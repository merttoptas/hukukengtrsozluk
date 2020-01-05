package com.merttoptas.hukukengtrsozluk.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.merttoptas.hukukengtrsozluk.R
import com.merttoptas.hukukengtrsozluk.db.Favorite
import com.merttoptas.hukukengtrsozluk.db.model.Words
import com.merttoptas.hukukengtrsozluk.utilities.Utils

class WordsAdapter(options: FirestorePagingOptions<Words>) : FirestorePagingAdapter<Words, WordsAdapter.WordsViewHolder>(options){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.word_item,
            parent, false
        )

        return WordsViewHolder(v)
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int, model: Words) {

        holder.bind(model)

        holder.tvWords
        val db = Utils.buildDatabase()
        val dao = Utils.getDAO()
        val favWord = Favorite(model.english, model.turkish).favWordsEng
        val myId = dao.getId(favWord)
        val favorite= db.favdao().getWordsFavorites()

        holder.tv_fav_button.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener {
                buttonView, isChecked ->


            if(favorite.equals(myId)){
                holder.tv_fav_button.isChecked = true


            } else{
                if(myId !=0){
                    var myId = dao.getId(favWord)
                    dao.deleteById(myId)
                    db.close()

                }
                if(isChecked){

                    val favWords= Favorite(model.english, model.turkish)
                    dao.insertWord(favWords)
                    db.close()
                    holder.tv_fav_button.isChecked = true

                }else{
                    dao.deleteById(myId)
                    db.close()
                    holder.tv_fav_button.isChecked = false

                }

            }

        })


        if (model != null) holder.bind(model)

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


    class WordsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

         var tvWords: TextView = itemView.findViewById(R.id.tv_words)
         var tv_fav_button: ToggleButton = itemView.findViewById(R.id.tv_fav_button)

        fun bind(wods: Words) {
            tvWords.text = wods.english
            tv_fav_button.isChecked = wods.fav

        }
    }

}

