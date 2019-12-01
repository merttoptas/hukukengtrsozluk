@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.merttoptas.hukukengtrsozluk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.graphics.toColorInt
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_words_activitiy.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.app_bar_main.toolbar

class WordsActivity : AppCompatActivity() {

    lateinit var searchFragment : SearchFragment
    lateinit var  fm : FragmentManager
    lateinit  var fragmentid : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words_activitiy)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Hukuk Sözlüğü"
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)
        navigation.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener)

        when {
            intent.hasExtra("fragmentFavorite") -> {
                fragmentid = intent.getStringExtra("fragmentFavorite")

                navigation.selectedItemId = R.id.navigationFavorites

            }
            intent.hasExtra("fragmentSearch") -> {
                fragmentid = intent.getStringExtra("fragmentSearch")
                navigation.selectedItemId = R.id.navigationSearch
            }
            intent.hasExtra("fragmentAboutUs") -> {
                fragmentid = intent.getStringExtra("fragmentAboutUs")
                navigation.selectedItemId = R.id.navigationAboutUs
            }
        }

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
            menuItem ->
        when (menuItem.itemId){

            R.id.navigationFavorites -> {
                val fragment = FavoriteFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment,
                    fragment.javaClass.simpleName).commit()

                return@OnNavigationItemSelectedListener true
            }

            R.id.navigationSearch -> {
                val fragment = SearchFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment,
                    fragment.javaClass.simpleName).commit()
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigationAboutUs -> {
                return@OnNavigationItemSelectedListener true
            }

        }
        false

    }



}
