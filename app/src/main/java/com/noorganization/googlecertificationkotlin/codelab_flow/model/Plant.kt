package com.noorganization.googlecertificationkotlin.codelab_flow.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plants")
data class Plant(
    @PrimaryKey @ColumnInfo(name = "id") val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringInterval: Int = 7, // how often the plant should be watered, in days
    val imageUrl: String = ""
) {
    override fun toString() = name
}


inline class GrowZone(val number: Int)
val NoGrowZone = GrowZone(-1)