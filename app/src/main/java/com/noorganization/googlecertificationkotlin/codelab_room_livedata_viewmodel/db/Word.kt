package com.noorganization.googlecertificationkotlin.codelab_room_livedata_viewmodel.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class Word(
    @field:PrimaryKey
    @field:ColumnInfo(name = "word")
    val word: String
)