package com.merttoptas.hukukengtrsozluk


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicolettilu.hiddensearchwithrecyclerview.HiddenSearchWithRecyclerView

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View? {
        // Inflate the layout for this fragment
        val view : View= inflater.inflate(R.layout.fragment_search, container, false)

        val hiddenSearchWithInRecycler = view!!.findViewById(R.id.hidden_search_with_recycler) as HiddenSearchWithRecyclerView
        hiddenSearchWithInRecycler.hideAtScroll = true
        hiddenSearchWithInRecycler.visibleAtInit = false
        hiddenSearchWithInRecycler.scrollToBottomBeforeHide = true
        hiddenSearchWithInRecycler.scrollToTopBeforeShow = false
        hiddenSearchWithInRecycler.filterWhileTyping = true

        return  view
    }


}
