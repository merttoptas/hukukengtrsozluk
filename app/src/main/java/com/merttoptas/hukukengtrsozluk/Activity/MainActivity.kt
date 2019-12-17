package com.merttoptas.hukukengtrsozluk.Activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.merttoptas.hukukengtrsozluk.Fragment.AboutUsFragment
import com.merttoptas.hukukengtrsozluk.Fragment.FavoriteFragment
import com.merttoptas.hukukengtrsozluk.Fragment.SearchFragment
import com.merttoptas.hukukengtrsozluk.R
import com.merttoptas.hukukengtrsozluk.utilities.Utils
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var fragmentId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTypeFace()

    }

    private fun  setTypeFace(){
        val typeface = Typeface.createFromAsset(assets, "SourceSansPro-Regular.ttf")
        tv_law.typeface = typeface
        tv_law_subtitle.typeface = typeface
    }

    fun wordsLearnOnClick(view: View) {
        val intent = Intent(this, LearnWordsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.flags =Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
    fun wordsOnClick(view: View) {
        val fragmentSearch = SearchFragment()
        fragmentId = fragmentSearch.toString()

        Utils.startIntent(Utils.context, WordsActivity::class.java, "fragmentSearch", fragmentId)

    }
    fun favoriteOnClick(view: View) {
        val fragmentFavorite = FavoriteFragment()
        fragmentId = fragmentFavorite.toString()

        Utils.startIntent(Utils.context, WordsActivity::class.java, "fragmentFavorite", fragmentId)

    }
    fun aboutUsOnClick(view: View) {
        val fragmentAbout = AboutUsFragment()
        fragmentId = fragmentAbout.toString()
        Utils.startIntent(Utils.context,  WordsActivity::class.java,"fragmentAboutUs", fragmentId)
    }


}
