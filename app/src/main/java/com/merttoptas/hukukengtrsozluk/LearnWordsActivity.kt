package com.merttoptas.hukukengtrsozluk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_learn_words.*

class LearnWordsActivity : AppCompatActivity() {
    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_words)

        easyFlipView.isFlipEnabled = true
        easyFlipView.setFlipTypeFromBack()

        toolbar = findViewById(R.id.toolbar)

        toolbar!!.title = "Kelime Öğrenme"
        toolbar!!.subtitle = "Hukuk - İngilizce Alıştırma"
        setSupportActionBar(toolbar)

    }
}
