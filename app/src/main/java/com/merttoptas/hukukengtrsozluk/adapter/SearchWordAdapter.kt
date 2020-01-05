package com.merttoptas.hukukengtrsozluk.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.merttoptas.hukukengtrsozluk.R
import com.merttoptas.hukukengtrsozluk.db.model.Words
import kotlinx.android.synthetic.main.search_word_item.view.*

class SearchWordAdapter : PagedListAdapter<Words, ProductViewHolder>(SearchWordAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_word_item, parent, false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val words = getItem(position)

        if (words != null) holder.bind(words)
    }

    companion object : DiffUtil.ItemCallback<Words>() {

        override fun areItemsTheSame(
            oldItem: Words,
            newItem: Words
        ): Boolean {
            return oldItem::class == newItem::class
        }

        override fun areContentsTheSame(
            oldItem: Words,
            newItem: Words
        ): Boolean {
            return oldItem.english == newItem.english
        }
    }

}

class ProductViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(words: Words) {
        view.itemName.text = words.english
    }
}
