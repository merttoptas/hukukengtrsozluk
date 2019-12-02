package com.merttoptas.hukukengtrsozluk.Activity

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.merttoptas.hukukengtrsozluk.Fragment.AboutUsFragment
import com.merttoptas.hukukengtrsozluk.Fragment.FavoriteFragment
import com.merttoptas.hukukengtrsozluk.Fragment.SearchFragment
import com.merttoptas.hukukengtrsozluk.R
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var fragmentId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTypeFace()
    }

    private fun  setTypeFace(){
        val typeface = Typeface.createFromAsset(assets, "fonts/SourceSansPro-Regular.ttf")
        tv_law.typeface = typeface
        tv_law_subtitle.typeface = typeface
    }

    fun wordsLearnOnClick(view: View) {
        val intent =Intent(this, LearnWordsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
    fun wordsOnClick(view: View) {
        val fragmentSearch =
            SearchFragment()
        fragmentId = fragmentSearch.toString()
        val intent = Intent(this, WordsActivity::class.java)
        intent.putExtra("fragmentSearch", fragmentId)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
    fun favoriteOnClick(view: View) {
        val fragmentFavorite =
            FavoriteFragment()
        fragmentId = fragmentFavorite.toString()
        val intent = Intent(this, WordsActivity::class.java)
        intent.putExtra("fragmentFavorite", fragmentId)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
    fun aboutUsOnClick(view: View) {
        val fragmentAbout =
            AboutUsFragment()
        fragmentId = fragmentAbout.toString()
        val intent = Intent(this, WordsActivity::class.java)
        intent.putExtra("fragmentAboutUs", fragmentId)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
}
