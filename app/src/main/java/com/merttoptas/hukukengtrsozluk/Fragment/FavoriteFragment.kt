package com.merttoptas.hukukengtrsozluk.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.merttoptas.hukukengtrsozluk.R
import com.merttoptas.hukukengtrsozluk.adapter.FavoriteWordsAdapter
import com.merttoptas.hukukengtrsozluk.db.Favorite
import com.merttoptas.hukukengtrsozluk.utilities.Utils
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {

    lateinit var favorite : List<Favorite>
    val db = Utils.buildDatabase()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View= inflater.inflate(R.layout.fragment_favorite, container, false)

        favorite= db.favdao().getWordsFavorites()

        val recyclerViewFav = view.findViewById<RecyclerView>(R.id.recyclerViewFav)

        Utils.setRecyclerView(context!!, recyclerViewFav)
        recyclerViewFav.adapter?.notifyDataSetChanged()
        recyclerViewFav.adapter =FavoriteWordsAdapter(favorite, this.context!!)


        return view
    }

    override fun onResume() {
        super.onResume()

        favorite = db.favdao().getWordsFavorites()
        recyclerViewFav.adapter?.notifyDataSetChanged()
        recyclerViewFav.adapter = FavoriteWordsAdapter(favorite, context!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }
}

