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

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val typeface = Typeface.createFromAsset(assets, "fonts/SourceSansPro-Regular.ttf")

        tv_law.typeface = typeface
        tv_law_subtitle.typeface = typeface

        supportActionBar!!.hide()
        val btnAnimation : Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.button_animation)


        cardView_words_learn.setOnClickListener {

            val intent =Intent(this, LearnWordsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
            menuItem ->
        when (menuItem.itemId){
            R.id.navigationHome -> {
                //val fragment = BlogFragment()
          //      supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
          //          .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigationFavorites -> {
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigationSearch -> {
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigationSettings -> {
                return@OnNavigationItemSelectedListener true
            }

        }
        false

    }

}
