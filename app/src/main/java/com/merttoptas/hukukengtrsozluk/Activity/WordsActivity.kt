@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.merttoptas.hukukengtrsozluk.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_words_activitiy.*
import androidx.appcompat.widget.Toolbar
import com.merttoptas.hukukengtrsozluk.Fragment.AboutUsFragment
import com.merttoptas.hukukengtrsozluk.Fragment.FavoriteFragment
import com.merttoptas.hukukengtrsozluk.Fragment.SearchFragment
import com.merttoptas.hukukengtrsozluk.Fragment.WordsListFragment
import com.merttoptas.hukukengtrsozluk.R

class WordsActivity : AppCompatActivity() {
    lateinit var fragmentid: String
    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words_activitiy)

        toolbar = findViewById(R.id.toolbar)
        toolbar!!.title = getString(R.string.law_dict)
        setSupportActionBar(toolbar)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        when {
            intent.hasExtra("fragmentFavorite") -> {
                fragmentid = intent.getStringExtra("fragmentFavorite")

                navigation.selectedItemId =
                    R.id.navigationFavorites

            }
            intent.hasExtra("fragmentSearch") -> {
                fragmentid = intent.getStringExtra("fragmentSearch")
                navigation.selectedItemId =
                    R.id.navigationSearch
            }
            intent.hasExtra("fragmentAboutUs") -> {
                fragmentid = intent.getStringExtra("fragmentAboutUs")
                navigation.selectedItemId =
                    R.id.navigationAboutUs
            }
        }

    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.navigationFavorites -> {
                    val fragment =
                        FavoriteFragment()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.container, fragment,
                        fragment.javaClass.simpleName
                    ).commit()

                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigationWords -> {
                    val fragment =
                        WordsListFragment()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.container, fragment,
                        fragment.javaClass.simpleName
                    ).commit()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigationSearch -> {

                    val fragment =
                        SearchFragment()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.container, fragment, fragment.javaClass.simpleName
                    ).commit()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigationAboutUs -> {
                    val fragment =
                        AboutUsFragment()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.container, fragment, fragment.javaClass.simpleName
                    ).commit()
                    return@OnNavigationItemSelectedListener true
                }

            }
            false

        }

}