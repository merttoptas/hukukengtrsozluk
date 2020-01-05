package com.merttoptas.hukukengtrsozluk.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.algolia.instantsearch.core.connection.ConnectionHandler
import com.algolia.instantsearch.helper.android.list.autoScrollToStart
import com.algolia.instantsearch.helper.android.searchbox.SearchBoxViewAppCompat
import com.algolia.instantsearch.helper.android.searchbox.connectView
import com.google.firebase.firestore.FirebaseFirestore
import com.merttoptas.hukukengtrsozluk.R
import com.merttoptas.hukukengtrsozluk.adapter.MyViewModel
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {
    val db = FirebaseFirestore.getInstance()
    private val connection = ConnectionHandler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_search, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val viewModel = ViewModelProviders.of(requireActivity())[MyViewModel::class.java]
        val searchBoxView = SearchBoxViewAppCompat(searchView)

        viewModel.products.observe(this, Observer { hits -> viewModel.adapterProduct.submitList(hits) })

        connection += viewModel.searchBox.connectView(searchBoxView)

        SrecyclerView.let {
            it.itemAnimator = null
            it.adapter = viewModel.adapterProduct
            it.layoutManager = LinearLayoutManager(requireContext())
            it.autoScrollToStart(viewModel.adapterProduct)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        connection.disconnect()
    }
}
