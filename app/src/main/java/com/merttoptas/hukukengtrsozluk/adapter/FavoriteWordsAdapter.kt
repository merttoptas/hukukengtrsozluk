package com.merttoptas.hukukengtrsozluk.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.merttoptas.hukukengtrsozluk.R
import com.merttoptas.hukukengtrsozluk.db.Favorite
import com.merttoptas.hukukengtrsozluk.utilities.Utils
import kotlinx.android.synthetic.main.fav_word_item.view.*
import kotlinx.android.synthetic.main.word_item.view.*
import kotlinx.android.synthetic.main.word_item.view.tv_fav_button

class FavoriteWordsAdapter (private val favorites: List<Favorite>, val context: Context):
    RecyclerView.Adapter<FavoriteWordsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteWordsAdapter.ViewHolder {
        val inflater =parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflater
            .inflate(R.layout.fav_word_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
      return  favorites.size
    }

    override fun onBindViewHolder(holder: FavoriteWordsAdapter.ViewHolder, position: Int) {

        val database = Utils.buildDatabase()
        val dao = Utils.getDAO()
        val favorite = favorites[position]
        holder.favWords.text = favorite.favWordsEng
        holder.favWordsTr.text = favorite.favWordsTr
        var favWord = Favorite(favorite.favWordsEng, favorite.favWordsTr).favWordsEng

        var myId = dao.getId(favWord)

        if(myId !=0){

            holder.favButton.isChecked=true

        }

        holder.favButton.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener{
            buttonView, isChecked ->

            if(isChecked){
                val favWords= Favorite(favorite.favWordsEng, favorite.favWordsTr)
                dao.insertWord(favWords)

                database.close()

                holder.favButton.isChecked = true
            }else {
                dao.deleteById(myId)
                database.close()
                holder.favButton.isChecked = false
            }
        })





    }
    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val favWords = view.tv_favWordsEng
        val favWordsTr = view.tv_favWordsTr
        val favButton = view.tv_fav_button
    }

}