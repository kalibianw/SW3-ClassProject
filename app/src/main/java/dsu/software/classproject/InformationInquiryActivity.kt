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

        val userPref = getSharedPreferences("user_details", MODE_PRIVATE)
        GetVisited(
            applicationContext,
            userPref.getString("userId", String()).toString(),
            tableLayout1
        ).start()
    }
}

class AddBeacon(val context: Context, val beaconId: Int, val location: String) : Thread() {
    override fun run() {
        Log.d("Start Notification", "Start AddBeacon")
        val Beacon = BeaconEntity(beaconId, location)
        BeaconDatabase.getInstance(context)!!.getBeaconDao().insert(Beacon)
    }
}

class GetVisited(val context: Context, val visitedUser: String, val tableLayout1: TableLayout) :
    Thread() {
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
            val beacon =
                BeaconDatabase.getInstance(context)!!.getBeaconDao().getValues(item.visitedLoc)
            Log.d("Value", "beacon: ${beacon}")
            textViewLoc.text = beacon[0].location

            val textViewTime = row.getChildAt(2) as TextView
            textViewTime.text = item.visitedTime
        }
    }
}
