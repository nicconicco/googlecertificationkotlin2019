package com.noorganization.googlecertificationkotlin.codelab_room_livedata_viewmodel

import android.app.Application
import com.noorganization.googlecertificationkotlin.codelab_room_livedata_viewmodel.db.Word
import androidx.lifecycle.LiveData
import com.noorganization.googlecertificationkotlin.codelab_room_livedata_viewmodel.db.WordRepository
import androidx.lifecycle.AndroidViewModel


class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: WordRepository = WordRepository(application)

    val allWords: LiveData<List<Word>>

    init {
        allWords = mRepository.allWords
    }

    fun insert(word: Word) {
        mRepository.insert(word)
    }
}