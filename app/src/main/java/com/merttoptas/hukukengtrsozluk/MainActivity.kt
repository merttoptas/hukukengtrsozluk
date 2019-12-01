package com.merttoptas.hukukengtrsozluk

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var bottomNavigationView: BottomNavigationView
        var fragmentId :String

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTypeFace()

        cardView_words_learn.setOnClickListener {

            val intent =Intent(this, LearnWordsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        cardView_words.setOnClickListener{
            val fragmentSearch = SearchFragment()
            fragmentId = fragmentSearch.toString()
            val intent = Intent(this, WordsActivity::class.java)
            intent.putExtra("fragmentSearch", fragmentId)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        cardview_favorite.setOnClickListener{
            val fragmentFavorite = FavoriteFragment()
            fragmentId = fragmentFavorite.toString()
            val intent = Intent(this, WordsActivity::class.java)
            intent.putExtra("fragmentFavorite", fragmentId)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
        cardview_about.setOnClickListener {
            val fragmentAbout = AboutUsFragment()
            fragmentId = fragmentAbout.toString()
            val intent = Intent(this, WordsActivity::class.java)
            intent.putExtra("fragmentAboutUs", fragmentId)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }

    }

    fun  setTypeFace(){
        val typeface = Typeface.createFromAsset(assets, "fonts/SourceSansPro-Regular.ttf")
        tv_law.typeface = typeface
        tv_law_subtitle.typeface = typeface
    }
}
