package com.noorganization.googlecertificationkotlin.codelab_room_livedata_viewmodel.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDao {

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun allWords(): LiveData<List<Word>>

    @Query("DELETE FROM word_table")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: Word)
}