package com.noorganization.googlecertificationkotlin.codelab_room_livedata_viewmodel.db

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData


class WordRepository internal constructor(application: Application) {

    private val mWordDao: WordDao
    internal val allWords: LiveData<List<Word>>

    init {
        val db = WordRoomDatabase.getDatabase(application)
        mWordDao = db.wordDao()
        allWords = mWordDao.allWords()
    }

    fun insert(word: Word) {
        InsertAsyncTask(mWordDao).execute(word)
    }

    private class InsertAsyncTask internal constructor(private val mAsyncTaskDao: WordDao) :
        AsyncTask<Word, Void, Void>() {

        override fun doInBackground(vararg params: Word): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }
}