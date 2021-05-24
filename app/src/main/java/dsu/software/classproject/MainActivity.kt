package dsu.software.classproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Start Notification", "Start MainActivity")

        val initUser = InitUser(applicationContext)
        initUser.start()
        val initVisited = InitVisited(applicationContext)
        initVisited.start()
        val initBeacon = InitBeacon(applicationContext)
        initBeacon.start()

        Thread.sleep(300)

        val getAllUser = GetAllUser(applicationContext)
        getAllUser.start()
        val getAllVisited = GetAllVisited(applicationContext)
        getAllVisited.start()
        val getAllBeacon = GetAllBeacon(applicationContext)
        getAllBeacon.start()

        val loginActivityIntent = Intent(this, LoginActivity::class.java)
        Log.d("Action Notification", "Init page logo button clicked.")
        startActivity(loginActivityIntent)
    }
}

class InitUser(val context: Context) : Thread() {
    override fun run() {
        Log.d("Start Notification", "Start InitUser")
        val user = UserEntity("010-1111-1111", "Pw01")
        UserDatabase.getInstance(context)!!.getUserDao().insert(user)

        val user2 = UserEntity("010-2222-2222", "Pw02")
        UserDatabase.getInstance(context)!!.getUserDao().insert(user2)
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

class GetAllUser(val context: Context) : Thread() {
    override fun run() {
        Log.d("Start Notification", "Start GetAllUser")

        val userItems = UserDatabase.getInstance(context)!!.getUserDao().getAllValues()
        Log.d("Value", "items: ${userItems}")
    }
}

class GetAllVisited(val context: Context) : Thread() {
    override fun run() {
        Log.d("Start Notification", "Start GetAllVisited")

        val visitedItems = VisitedDatabase.getInstance(context)!!.getVisitedDao().getAllValues()
        Log.d("Value", "items: ${visitedItems}")
    }
}

class GetAllBeacon(val context: Context) : Thread() {
    override fun run() {
        Log.d("Start Notification", "Start GetAllBeacon")

        val beaconItems = BeaconDatabase.getInstance(context)!!.getBeaconDao().getAllValues()
        Log.d("Value", "items: ${beaconItems}")
    }
}
