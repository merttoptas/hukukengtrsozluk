package com.merttoptas.hukukengtrsozluk.Fragment


import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.merttoptas.hukukengtrsozluk.R
import kotlinx.android.synthetic.main.fragment_about_us.*

class AboutUsFragment : Fragment() {
    lateinit var tvAnimation : Animation
    lateinit var btnAnimation : Animation
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View= inflater.inflate(R.layout.fragment_about_us, container, false)

        //Animation
        tvAnimation = AnimationUtils.loadAnimation(context, R.anim.tv_animation)
        btnAnimation = AnimationUtils.loadAnimation(context, R.anim.button_animation)

        val tvInfo  = view.findViewById<TextView>(R.id.tvInfo)
        val cardView = view.findViewById<CardView>(R.id.cardView)

        tvInfo.animation = tvAnimation
        cardView.animation = btnAnimation

        //TypeFace
        val typeface : Typeface = Typeface.createFromAsset(context!!.assets, "SourceSansPro-Regular.ttf")

        tvInfo.typeface = typeface

        return view
    }



}
