package com.merttoptas.hukukengtrsozluk.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.algolia.instantsearch.core.connection.ConnectionHandler
import com.algolia.instantsearch.core.searchbox.SearchBoxViewModel
import com.algolia.instantsearch.helper.android.list.SearcherSingleIndexDataSource
import com.algolia.instantsearch.helper.android.searchbox.SearchBoxConnectorPagedList
import com.algolia.instantsearch.helper.android.searchbox.connectSearcher
import com.algolia.instantsearch.helper.searchbox.SearchMode
import com.algolia.instantsearch.helper.searcher.SearcherSingleIndex
import com.algolia.search.client.ClientSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.merttoptas.hukukengtrsozluk.R
import com.merttoptas.hukukengtrsozluk.db.model.Words
import com.merttoptas.hukukengtrsozluk.utilities.Utils.Companion.context
import io.ktor.client.features.logging.LogLevel

class MyViewModel : ViewModel() {

    val client = ClientSearch(ApplicationID(context.getString(R.string.application_id)), APIKey(context.getString(
            R.string.api_key)), LogLevel.ALL)
    val index = client.initIndex(IndexName(context.getString(R.string.index_name)))
    val searcher = SearcherSingleIndex(index)
    val db = FirebaseFirestore.getInstance()
    val wordsRef  = db.collection("words")

    val dataSourceFactory = SearcherSingleIndexDataSource.Factory(searcher) { hit ->
        Words(
            hit.json.getPrimitive("english").content

        )
    }

    val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPrefetchDistance(2)
        .setPageSize(20)
        .build()

    val pagedListConfig = PagedList.Config.Builder().setPageSize(50).setEnablePlaceholders(false).build()
    val products: LiveData<PagedList<Words>> = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
    val searchBox = SearchBoxConnectorPagedList(searcher, listOf(products))
    val adapterProduct = SearchWordAdapter()


    val query: Query = wordsRef.orderBy(
        "english",
        Query.Direction.ASCENDING
    )

    val connection = ConnectionHandler()


    init {
        connection += searchBox

    }

    override fun onCleared() {
        super.onCleared()
        searcher.cancel()
        connection.clear()
    }
}