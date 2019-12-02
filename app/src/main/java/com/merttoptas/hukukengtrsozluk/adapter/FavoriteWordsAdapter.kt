package com.merttoptas.hukukengtrsozluk.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.merttoptas.hukukengtrsozluk.R
import kotlinx.android.synthetic.main.word_item.view.*

class FavoriteWordsAdapter (val favorites: List<FavoriteWordsAdapter>, val context: Context):
    RecyclerView.Adapter<FavoriteWordsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteWordsAdapter.ViewHolder {
        val inflater =parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflater
            .inflate(R.layout.word_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
      return  favorites.size
    }

    override fun onBindViewHolder(holder: FavoriteWordsAdapter.ViewHolder, position: Int) {

        val favorite = favorites[position]

    }
    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val favWords = view.tv_words
    }

}