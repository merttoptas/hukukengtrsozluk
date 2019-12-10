package com.merttoptas.hukukengtrsozluk.Fragment


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Query
import com.merttoptas.hukukengtrsozluk.R
import com.merttoptas.hukukengtrsozluk.adapter.WordsAdapter
import com.merttoptas.hukukengtrsozluk.db.model.Words
import com.nicolettilu.hiddensearchwithrecyclerview.HiddenSearchWithRecyclerView

class SearchFragment : Fragment() {
    val db = FirebaseFirestore.getInstance()
    val wordsRef  = db.collection("words")
    val getFavWords = Words()


    private var mAdapter: WordsAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View? {
        val view : View= inflater.inflate(R.layout.fragment_search, container, false)

        val activity = activity as Context

         val hiddenSearchWithInRecycler = view.findViewById(R.id.hidden_search_with_recycler)
                as HiddenSearchWithRecyclerView
        hiddenSearchWithInRecycler.hideAtScroll = true
        hiddenSearchWithInRecycler.visibleAtInit = false
        hiddenSearchWithInRecycler.scrollToBottomBeforeHide = true
        hiddenSearchWithInRecycler.scrollToTopBeforeShow = false
        hiddenSearchWithInRecycler.filterWhileTyping = true

        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        db.firestoreSettings = settings


        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val query: Query = wordsRef.orderBy(
            "english",
            Query.Direction.ASCENDING
        )


        val options: FirestoreRecyclerOptions<Words?>? = FirestoreRecyclerOptions.Builder<Words>()
            .setQuery(query, Words::class.java)
            .build()

        Log.d("query", options.toString())

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        mAdapter = WordsAdapter(options,activity)
        recyclerView.adapter = mAdapter



        return  view
    }

    override fun onStart() {
        super.onStart()
        mAdapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()
        mAdapter!!.stopListening()
    }

    override fun onPause() {
        super.onPause()
        mAdapter!!.stopListening()
    }


}
