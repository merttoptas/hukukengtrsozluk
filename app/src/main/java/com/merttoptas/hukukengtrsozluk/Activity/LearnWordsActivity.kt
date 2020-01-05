package com.merttoptas.hukukengtrsozluk.Activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.firestore.FirebaseFirestore
import com.merttoptas.hukukengtrsozluk.R
import com.merttoptas.hukukengtrsozluk.db.model.Words
import kotlinx.android.synthetic.main.activity_learn_words.*
import kotlinx.android.synthetic.main.words_card_layout_back.*
import kotlinx.android.synthetic.main.words_card_layout_front.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


class LearnWordsActivity : AppCompatActivity() {
    var toolbar: Toolbar? = null
    val db = FirebaseFirestore.getInstance()
    val wordsRef = db.collection("words")
    val randomWordsList: MutableList<Words?> = ArrayList()
    var learnWords : Int = 0
    var notLearnWords : Int =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_words)

        toolbar = findViewById(R.id.toolbar)
        toolbar!!.title = getString(R.string.word_learn)
        toolbar!!.subtitle = getString(R.string.learn_exercise)

        setSupportActionBar(toolbar)
        easyFlipView.isFlipOnTouch = true

        //random get data

        btnViewMeaning_cardfront.setOnClickListener {
            easyFlipView.setFlipTypeFromBack()
            easyFlipView.flipTheView()
        }

        btnViewYes_cardBack.setOnClickListener {
            learnWords++
            textNumber.text = learnWords.toString()
            easyFlipView.flipTheView()
            getData()

        }
        btnViewNo_cardBack.setOnClickListener {
            notLearnWords++
            tvNumber1.text = notLearnWords.toString()
            easyFlipView.flipTheView()
            getData()
        }

    }

    private fun getData() {
        //deneme
        val wordsCollectionReference = db.collection("words").orderBy("english")
        wordsCollectionReference.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val wordsList: MutableList<Words?> = ArrayList()

                    for (document in task.result!!)
                    {
                        val words: Words = document.toObject<Words>(Words::class.java)
                        wordsList.add(words)
                    }
                    val wordsListSize = wordsList.size
                    val randomWordsList: MutableList<Words?> = ArrayList()
                    for (i in 0 until wordsListSize) {
                        val randomWords: Words? = wordsList[Random.nextInt(wordsListSize)]
                        if (!randomWordsList.contains(randomWords)) {
                            randomWordsList.add(randomWords)

                            easyFlipView.isFlipOnTouch = true

                            GlobalScope.launch(context = Dispatchers.Main) {
                                delay(2000)
                                txtWord_cardback.text = randomWords!!.turkish
                            }
                            GlobalScope.launch(context = Dispatchers.Main) {
                                txtWord_cardfront.text = randomWords!!.english

                            }
                        }
                    }
                }
                else {
                    Log.d(
                        "ErrorDb", "Error getting documents: ",
                        task.exception
                    )
                }
            }
    }

    override fun onStart() {
        super.onStart()
        getData()
    }


}
