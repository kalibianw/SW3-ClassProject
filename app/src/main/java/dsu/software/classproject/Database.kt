package dsu.software.classproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [VisitedEntity::class], version = 2)
abstract class VisitedDatabase : RoomDatabase() {
    abstract fun getVisitedDao(): VisitedDao

    companion object {
        private var INSTANCE: VisitedDatabase? = null

        fun getInstance(context: Context): VisitedDatabase? {
            if (INSTANCE == null) {
                synchronized(VisitedDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        VisitedDatabase::class.java,
                        "visited.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}

@Database(entities = [BeaconEntity::class], version = 2)
abstract class BeaconDatabase : RoomDatabase() {
    abstract fun getBeaconDao(): BeaconDao

    companion object {
        private var INSTANCE: BeaconDatabase? = null

        fun getInstance(context: Context): BeaconDatabase? {
            if (INSTANCE == null) {
                synchronized(BeaconDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BeaconDatabase::class.java,
                        "beacon.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}
