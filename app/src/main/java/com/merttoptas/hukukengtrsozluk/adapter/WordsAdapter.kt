package com.merttoptas.hukukengtrsozluk.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.ToggleButton
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.merttoptas.hukukengtrsozluk.R
import com.merttoptas.hukukengtrsozluk.db.Favorite
import com.merttoptas.hukukengtrsozluk.db.model.Words
import com.merttoptas.hukukengtrsozluk.utilities.Utils
import kotlinx.android.synthetic.main.word_item.*


class WordsAdapter (@NonNull options: FirestoreRecyclerOptions<Words?>?,  val context: Context) :
    FirestoreRecyclerAdapter<Words, WordsAdapter.WordsHolder>(options!!)

{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.word_item,
            parent, false
        )
        return WordsHolder(v)
    }
    override fun onBindViewHolder(holder: WordsHolder, position: Int, model: Words) {
        holder.tv_words.text = model.english

        val database = Utils.buildDatabase()
        val dao = Utils.getDAO()
        var favorite = ArrayList<Favorite>()
        Log.d("favorites", favorite.toString())
        var favWord = Favorite(model.english, model.turkish).favWordsEng



        var myId = dao.getId(favWord)

        holder.tv_fav_button.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener {
                buttonView, isChecked ->

            if(isChecked){

                val favWords= Favorite(model.english, model.turkish)
                Log.d("favoo", favWords.toString())
                dao.insertWord(favWords)

                database.close()

                holder.tv_fav_button.isChecked = true

            }else{
                dao.deleteById(myId)
                database.close()
                holder.tv_fav_button.isChecked = false
            }

        })

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