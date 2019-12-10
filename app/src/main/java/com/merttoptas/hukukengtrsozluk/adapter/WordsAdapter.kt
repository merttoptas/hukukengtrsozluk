package com.merttoptas.hukukengtrsozluk.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ToggleButton
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.merttoptas.hukukengtrsozluk.R
import com.merttoptas.hukukengtrsozluk.db.model.Words


class WordsAdapter (@NonNull options: FirestoreRecyclerOptions<Words?>?,  val context: Context) :
    FirestoreRecyclerAdapter<Words, WordsAdapter.WordsHolder>(options!!) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.word_item,
            parent, false
        )
        return WordsHolder(v)
    }
    override fun onBindViewHolder(holder: WordsHolder, position: Int, model: Words) {
        holder.tv_words.text = model.english

    }

    inner class WordsHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tv_words: TextView
        var tv_fav_button: ToggleButton

        init {
            tv_words = itemView.findViewById(R.id.tv_words)
            tv_fav_button = itemView.findViewById(R.id.tv_fav_button)
        }
    }



}