package dsu.software.classproject

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beacon")
data class BeaconEntity(
    @PrimaryKey val id: Int,
    val location: String
)

@Entity(tableName = "visited")
data class VisitedEntity(
    @PrimaryKey val idx: Int,
    val visitedUser: String,
    val visitedDate: String,
    val visitedLoc: Int,
    val visitedTime: String
)
