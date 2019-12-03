package com.noorganization.googlecertificationkotlin.codelab_room_coroutines.db

import androidx.lifecycle.LiveData
import com.noorganization.googlecertificationkotlin.codelab_room_livedata_viewmodel.db.Word
import com.noorganization.googlecertificationkotlin.codelab_room_livedata_viewmodel.db.WordDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WordRepository(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Word>> = wordDao.allWords()

    suspend fun insert(word: Word) = withContext(Dispatchers.IO) {
        wordDao.insert(word)
    }
}