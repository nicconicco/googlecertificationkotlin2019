package com.example.android.roomwordssample

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.noorganization.googlecertificationkotlin.codelab_room_coroutines.db.WordRoomCoroutinesDatabase
import com.noorganization.googlecertificationkotlin.codelab_room_livedata_viewmodel.db.Word
import com.noorganization.googlecertificationkotlin.codelab_room_livedata_viewmodel.db.WordDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException


/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(JUnit4::class)
class WordDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var wordDao: WordDao
    private lateinit var db: WordRoomCoroutinesDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, WordRoomCoroutinesDatabase::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()
        wordDao = db.wordDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetWord() = runBlocking {
        val word = Word("word")
        wordDao.insert(word)
        val allWords = wordDao.allWords().waitForValue()
        assertEquals(allWords[0].word, word.word)
    }

    @Test
    @Throws(Exception::class)
    fun getAllWords() = runBlocking {
        val word = Word("aaa")
        wordDao.insert(word)
        val word2 = Word("bbb")
        wordDao.insert(word2)
        val allWords = wordDao.allWords().waitForValue()
        assertEquals(allWords[0].word, word.word)
        assertEquals(allWords[1].word, word2.word)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAll() = runBlocking {
        val word = Word("word")
        wordDao.insert(word)
        val word2 = Word("word2")
        wordDao.insert(word2)
        wordDao.deleteAll()
        val allWords = wordDao.allWords().waitForValue()
        assertTrue(allWords.isEmpty())
    }
}
