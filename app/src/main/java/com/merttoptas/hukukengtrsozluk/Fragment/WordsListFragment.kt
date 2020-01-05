@file:Suppress("NAME_SHADOWING")

package com.merttoptas.hukukengtrsozluk.Fragment


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.algolia.instantsearch.helper.android.list.autoScrollToStart
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.merttoptas.hukukengtrsozluk.R
import com.merttoptas.hukukengtrsozluk.adapter.MyViewModel
import com.merttoptas.hukukengtrsozluk.adapter.WordsAdapter
import com.merttoptas.hukukengtrsozluk.db.model.Words
import kotlinx.android.synthetic.main.fragment_words.*


class WordsListFragment : Fragment() {
    val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View? {
        val view : View= inflater.inflate(R.layout.fragment_words, container, false)
        firebaseMemory()

        return  view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val options = FirestorePagingOptions.Builder<Words>()
            .setLifecycleOwner(this)
            .setQuery(MyViewModel().query, MyViewModel().config, Words::class.java)
            .build()

        val adapterWords = WordsAdapter(options)

        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)

        swipeRefreshLayout.setOnRefreshListener(OnRefreshListener {

            adapterWords.refresh()
            adapterWords.notifyDataSetChanged()
            swipeRefreshLayout.isRefreshing = false
        })

        recyclerView.let {
            it.itemAnimator = null
            it.adapter = adapterWords
            it.layoutManager = LinearLayoutManager(requireContext())
            it.autoScrollToStart(adapterWords)
        }

    }


    private fun firebaseMemory(){

        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        db.firestoreSettings = settings
    }

}



