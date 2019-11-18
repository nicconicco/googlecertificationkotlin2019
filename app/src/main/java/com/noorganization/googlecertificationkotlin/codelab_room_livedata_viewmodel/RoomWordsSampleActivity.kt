package codelab_room_livedata_viewmodel

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.noorganization.googlecertificationkotlin.R
import com.noorganization.googlecertificationkotlin.codelab_room_livedata_viewmodel.NewWordActivity
import com.noorganization.googlecertificationkotlin.codelab_room_livedata_viewmodel.WordListAdapter
import com.noorganization.googlecertificationkotlin.codelab_room_livedata_viewmodel.WordViewModel
import com.noorganization.googlecertificationkotlin.codelab_room_livedata_viewmodel.db.Word
import kotlinx.android.synthetic.main.activity_room_words_sample.*

private const val NEW_WORD_ACTIVITY_REQUEST_CODE = 1

class RoomWordsSampleActivity : AppCompatActivity() {

    private lateinit var adapter: WordListAdapter
    private var mWordViewModel: WordViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_words_sample)

        setRecyclerView()
        setViewModel()

        fab.setOnClickListener {
            startNewWordActivity()
        }
    }

    private fun startNewWordActivity() {
        val intent = Intent(this@RoomWordsSampleActivity, NewWordActivity::class.java)
        startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
    }

    private fun setViewModel() {
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        mWordViewModel!!.allWords.observe(this,
            Observer<List<Word>> { words ->
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words)
            })
    }

    private fun setRecyclerView() {
        val recyclerView =
            findViewById<RecyclerView>(R.id.recyclerview)
        adapter = WordListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val word = Word(data!!.getStringExtra(NewWordActivity.EXTRA_REPLY)!!)
            mWordViewModel?.insert(word)
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}


