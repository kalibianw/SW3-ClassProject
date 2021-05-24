package dsu.software.classproject

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VisitedDao {
    @Query("SELECT * FROM visited WHERE visitedUser = :visitedUser")
    fun getValues(visitedUser: String): List<VisitedEntity>

    @Query("SELECT * FROM visited")
    fun getAllValues(): List<VisitedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(visitedEntity: VisitedEntity)

    @Query("UPDATE visited set visitedUser = :visitedUser, visitedDate = :visitedDate, visitedLoc = :visitedLoc, visitedTime = :visitedTime WHERE idx = :idx")
    fun update(idx: Int, visitedUser: String, visitedDate: String, visitedLoc: Int, visitedTime: String)

    @Query("DELETE FROM visited WHERE idx = :idx")
    fun delete(idx: Int)
}

@Dao
interface BeaconDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(BeaconEntity: BeaconEntity)

    @Query("SELECT * FROM beacon")
    fun getAllValues(): List<BeaconEntity>

    @Query("SELECT * FROM beacon WHERE id = :id")
    fun getValues(id: Int): List<BeaconEntity>
}
