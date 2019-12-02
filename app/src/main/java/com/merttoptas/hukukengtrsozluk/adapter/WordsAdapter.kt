package com.merttoptas.hukukengtrsozluk.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.merttoptas.hukukengtrsozluk.R
import com.merttoptas.hukukengtrsozluk.db.model.Words

class WordsAdapter(val context: Context, var mWordsList :ArrayList<Words>) : RecyclerView.Adapter<WordsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsAdapter.ViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflater
            .inflate(R.layout.word_item, parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  mWordsList.size
    }

    override fun onBindViewHolder(holder: WordsAdapter.ViewHolder, position: Int) {
        val wordsList = mWordsList[position]

    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
          internal var tvWords :TextView
          internal var tv_fav_button:ToggleButton

        init {
            tvWords =itemView.findViewById(R.id.tv_words)
            tv_fav_button = itemView.findViewById(R.id.tv_fav_button)

        }
    }
}