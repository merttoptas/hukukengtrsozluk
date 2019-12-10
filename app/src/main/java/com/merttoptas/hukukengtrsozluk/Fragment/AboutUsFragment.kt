package com.merttoptas.hukukengtrsozluk.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.merttoptas.hukukengtrsozluk.R

/**
 * A simple [Fragment] subclass.
 */
class AboutUsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View= inflater.inflate(R.layout.fragment_about_us, container, false)

        return view
    }


}
