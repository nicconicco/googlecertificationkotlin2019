package com.noorganization.googlecertificationkotlin.codelab_room_livedata_viewmodel

import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.noorganization.googlecertificationkotlin.codelab_room_livedata_viewmodel.db.Word
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.noorganization.googlecertificationkotlin.R


class WordListAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mWords: List<Word>? = null // Cached copy of words

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if (mWords != null) {
            val current = mWords!![position]
            holder.wordItemView.text = current.word
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.text = "No Word"
        }
    }

    internal fun setWords(words: List<Word>) {
        mWords = words
        notifyDataSetChanged()
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    override fun getItemCount(): Int {
        return if (mWords != null)
            mWords!!.size
        else
            0
    }

    inner class WordViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
    }
}