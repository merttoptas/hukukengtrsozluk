package com.merttoptas.hukukengtrsozluk.Fragment


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Query
import com.merttoptas.hukukengtrsozluk.R
import com.merttoptas.hukukengtrsozluk.adapter.WordsAdapter
import com.merttoptas.hukukengtrsozluk.db.Favorite
import com.merttoptas.hukukengtrsozluk.db.model.Words
import com.merttoptas.hukukengtrsozluk.utilities.Utils
import com.nicolettilu.hiddensearchwithrecyclerview.HiddenSearchWithRecyclerView
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {
    val db = FirebaseFirestore.getInstance()
    val wordsRef  = db.collection("words")
    var words : MutableList<Words> = ArrayList()
    private var mAdapter: WordsAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View? {
        val view : View= inflater.inflate(R.layout.fragment_search, container, false)

        val activity = activity as Context
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

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

        val query: Query = wordsRef.orderBy(
            "english",
            Query.Direction.ASCENDING
        )
        val options: FirestoreRecyclerOptions<Words?>? = FirestoreRecyclerOptions.Builder<Words>()
            .setQuery(query, Words::class.java)
            .build()


        Utils.setRecyclerView(context!!, recyclerView)
        mAdapter = WordsAdapter(options,activity)
        recyclerView.adapter = mAdapter
        mAdapter!!.notifyDataSetChanged()


        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)

        val manager = context!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", true)
                searchView.onActionViewCollapsed()
                Toast.makeText(context, "Looking for $query", Toast.LENGTH_LONG).show()

                return true

            }
            override fun onQueryTextChange(newText: String?): Boolean {
                Toast.makeText(context, "Looking for $newText", Toast.LENGTH_LONG).show()
                var displayList : MutableList<Words> = ArrayList()

                words.clear()
                if(newText!!.isNotEmpty()){
                   wordsRef.whereArrayContains("english", newText)

                }

                recyclerView.adapter = mAdapter
                mAdapter!!.notifyDataSetChanged()
                return true


            }

        })

    }


}

