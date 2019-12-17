package com.merttoptas.hukukengtrsozluk.adapter

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Filterable
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
    override fun onBindViewHolder(holder: WordsHolder, position: Int, model: Words  ) {

        holder.tv_words.text = model.english
        //Database
        val db = Utils.buildDatabase()
        val dao = Utils.getDAO()
        val favWord = Favorite(model.english, model.turkish).favWordsEng
        val myId = dao.getId(favWord)
        val favorite= db.favdao().getWordsFavorites()

        val editor: SharedPreferences.Editor = context.getSharedPreferences(
            "com.merttoptas.hukukengtrsozluk",
            MODE_PRIVATE
        ).edit()


        holder.tv_fav_button.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener {
                buttonView, isChecked ->


            if(favorite.equals(myId)){
                holder.tv_fav_button.isChecked = true
                Log.d("deneme","tıklandı1")


            } else{
                if(myId !=0){
                    var myId = dao.getId(favWord)
                    dao.deleteById(myId)
                    db.close()
                    Log.d("deneme","tıklandı2")

                }



                if(isChecked){

                    val favWords= Favorite(model.english, model.turkish)
                    dao.insertWord(favWords)
                    db.close()

                    editor.putBoolean("favTrue", true)
                    editor.apply()
                    Log.d("deneme","tıklandı3")

                    holder.tv_fav_button.isChecked = true

                }else{
                    dao.deleteById(myId)
                    db.close()
                    holder.tv_fav_button.isChecked = false
                    val editor: SharedPreferences.Editor = context.getSharedPreferences(
                        "com.merttoptas.hukukengtrsozluk",
                        MODE_PRIVATE
                    ).edit()
                    editor.putBoolean("NameOfThingToSave", false)
                    editor.apply()
                    Log.d("deneme","tıklandı4")

                }

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