package com.merttoptas.hukukengtrsozluk.Activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.merttoptas.hukukengtrsozluk.R
import com.merttoptas.hukukengtrsozluk.db.model.Words
import kotlinx.android.synthetic.main.activity_learn_words.*
import kotlinx.android.synthetic.main.words_card_layout_back.*
import kotlinx.android.synthetic.main.words_card_layout_front.*

import kotlin.random.Random


class LearnWordsActivity : AppCompatActivity() {
    var toolbar: Toolbar? = null
    val db = FirebaseFirestore.getInstance()
    val wordsRef  = db.collection("words")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_words)

        easyFlipView.isFlipEnabled = true
        easyFlipView.setFlipTypeFromBack()

        toolbar = findViewById(R.id.toolbar)

        toolbar!!.title = getString(R.string.word_learn)
        toolbar!!.subtitle = getString(R.string.learn_exercise)
        setSupportActionBar(toolbar)

        //random get data
        val wordsCollectionReference = db.collection("words")
        wordsCollectionReference.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val wordsList: MutableList<Words?> = ArrayList()
                    for (document in task.result!!) {
                        val words: Words = document.toObject<Words>(Words::class.java)
                        wordsList.add(words)
                    }
                    val wordsListSize = wordsList.size
                    val randomWordsList: MutableList<Words?> =
                        ArrayList()
                    for (i in 0 until wordsListSize) {
                        val randomWords: Words? =
                            wordsList[Random.nextInt(wordsListSize)]
                        Log.d("words", wordsList.toString())
                        if (!randomWordsList.contains(randomWords)) {
                            randomWordsList.add(randomWords)
                            Log.d("words1",randomWordsList.toString())

                            txtWord_cardfront.text = randomWordsList[0].toString()


                        }
                    }
                } else {
                    Log.d(
                        "Deneme", "Error getting documents: ",
                        task.exception
                    )
                }
            }


    }

    override fun onStart() {
        super.onStart()
        getData()
    }

    private fun getData(){
        val docRef = db.collection("words").document("EwQIBpv0dVeYXwd5Crnw")
        docRef.get()
            .addOnSuccessListener {
                    document ->
                if(document !=null){
                    Log.d("exist", "${document.data}")

                  //  txtWord_cardfront.text = document.getString("english")
                    txtWord_cardback.text = document.getString("turkish")

                }else{
                    Log.d("noExist", "No such document")
                }
            }
            .addOnFailureListener{
                    exception ->
                Log.d("errordb", "get failed with", exception)
            }

    }
}
