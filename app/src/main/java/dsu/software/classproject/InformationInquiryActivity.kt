package dsu.software.classproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class InformationInquiryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_inquiry)

        Log.d("Start Notification", "Start InformationInquiryActivity")

        val tableLayout1 = findViewById<TableLayout>(R.id.tableLayout1)

        val initVisited = InitVisited(applicationContext)
        initVisited.start()
        val initBeacon = InitBeacon(applicationContext)
        initBeacon.start()

        val addVisited = AddVisited(
            applicationContext,
            3,
            "010-2222-2222",
            "2021-01-02",
            3,
            "03:00"
        )
        addVisited.start()

        Thread.sleep(500)

        val getAllVisited = GetAllVisited(applicationContext)
        getAllVisited.start()
        val getAllBeacon = GetAllBeacon(applicationContext)
        getAllBeacon.start()

        val getVisited = GetVisited(applicationContext, "010-1111-1111", tableLayout1)
        getVisited.start()
    }
}

class InitVisited(val context: Context) : Thread() {
    override fun run() {
        Log.d("Start Notification", "Start InitVisited")
        val visited = VisitedEntity(0, "010-1111-1111", "2021-01-01", 0, "00:00")
        VisitedDatabase.getInstance(context)!!.getVisitedDao().insert(visited)

        val visited2 = VisitedEntity(1, "010-1111-1111", "2021-01-02", 1, "01:00")
        VisitedDatabase.getInstance(context)!!.getVisitedDao().insert(visited2)

        val visited3 = VisitedEntity(2, "010-2222-2222", "2021-01-01", 2, "02:00")
        VisitedDatabase.getInstance(context)!!.getVisitedDao().insert(visited3)
    }
}

class InitBeacon(val context: Context) : Thread() {
    override fun run() {
        Log.d("Start Notification", "Start InitBeacon")
        val beacon = BeaconEntity(0, "장소 0")
        BeaconDatabase.getInstance(context)!!.getBeaconDao().insert(beacon)
        val beacon2 = BeaconEntity(1, "장소 1")
        BeaconDatabase.getInstance(context)!!.getBeaconDao().insert(beacon2)
        val beacon3 = BeaconEntity(2, "장소 2")
        BeaconDatabase.getInstance(context)!!.getBeaconDao().insert(beacon3)
    }
}

class AddVisited(
    val context: Context,
    val idx: Int,
    val visitedUser: String,
    val visitedDate: String,
    val visitedLoc: Int,
    val visitedTime: String
) : Thread() {
    override fun run() {
        Log.d("Start Notification", "Start AddVisited")
        val visited = VisitedEntity(idx, visitedUser, visitedDate, visitedLoc, visitedTime)
        VisitedDatabase.getInstance(context)!!.getVisitedDao().insert(visited)
    }
}

class AddBeacon(val context: Context, val id: Int, val location: String) : Thread() {
    override fun run() {
        Log.d("Start Notification", "Start AddBeacon")
        val Beacon = BeaconEntity(id, location)
        BeaconDatabase.getInstance(context)!!.getBeaconDao().insert(Beacon)
    }
}

class UpdateVisited(
    val context: Context,
    val idx: Int,
    val visitedUser: String,
    val visitedDate: String,
    val visitedLoc: Int,
    val visitedTime: String
) : Thread() {
    override fun run() {
        VisitedDatabase.getInstance(context)!!.getVisitedDao()
            .update(idx, visitedUser, visitedDate, visitedLoc, visitedTime)
    }
}

class GetVisited(val context: Context, val visitedUser: String, val tableLayout1: TableLayout) : Thread() {
    override fun run() {
        Log.d("Start Notification", "Start GetVisited")

        val items = VisitedDatabase.getInstance(context)!!.getVisitedDao().getValues(visitedUser)
        Log.d("Value", "items.size: ${items.size}")

        for (row_count in 1 until items.size + 1) {
            val row = tableLayout1.getChildAt(row_count) as TableRow
            val item = items[row_count - 1]
            Log.d("Value", "item: ${item}")
            val textViewDate = row.getChildAt(0) as TextView
            textViewDate.text = item.visitedDate

            val textViewLoc = row.getChildAt(1) as TextView
            val beacon = BeaconDatabase.getInstance(context)!!.getBeaconDao().getValues(item.visitedLoc)
            Log.d("Value", "beacon: ${beacon}")
            textViewLoc.text = beacon[0].location

            val textViewTime = row.getChildAt(2) as TextView
            textViewTime.text = item.visitedTime
        }
    }
}

class GetAllVisited(val context: Context) : Thread() {
    override fun run() {
        Log.d("Start Notification", "Start GetAllVisited")

        val items = VisitedDatabase.getInstance(context)!!.getVisitedDao().getAllValues()
        Log.d("Value", "items: ${items}")
    }
}

class GetAllBeacon(val context: Context) : Thread() {
    override fun run() {
        Log.d("Start Notification", "Start GetAllBeacon")

        val items = BeaconDatabase.getInstance(context)!!.getBeaconDao().getAllValues()
        Log.d("Value", "items: ${items}")
    }
}
