package com.bbrustol.maytheforcebewithbruno.presentation.list_navigation.starwarsPeopleList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bbrustol.maytheforcebewithbruno.R
import com.bbrustol.maytheforcebewithbruno.data.entity.Result
import com.bbrustol.maytheforcebewithbruno.databinding.FragmentStarwarsListBinding
import com.bbrustol.maytheforcebewithbruno.utils.Constants
import com.bbrustol.maytheforcebewithbruno.utils.hideKeyboard
import com.bbrustol.maytheforcebewithbruno.utils.loadMore
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.fragment_starwars_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class StarwarsPeopleListFragment : Fragment() {

    private var flagSearchSummit: Boolean = false
    private val viewModel: StarwarsPeopleListViewModel by viewModel()

    private lateinit var dataBinding: FragmentStarwarsListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_starwars_list, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            super.onSaveInstanceState(savedInstanceState)
        }
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = this
        dataBinding.executePendingBindings()

        initializeRecyclerView(view)
        setupListeners()

        if (!viewModel.flagFirstLoad.value!!) {
            viewModel.start(viewModel.receivedSearch.value!!)
            viewModel.flagFirstLoad.value = true
        }
    }

    private fun initializeRecyclerView(view: View) {
        dataBinding.rvStarwarsPeopleList.layoutManager = LinearLayoutManager(context)

        val rvClickAction = { result: Result ->
            this.hideKeyboard()
            val bundle = Bundle().apply { putParcelable(Constants.EXTRA_STARWARS_PEOPLE_SELECTED_RESULT, result) }
            view.findNavController().navigate(R.id.actionStarwarsListToDetail, bundle)
        }

        val favoriteClickAction = { result: Result ->
            val response = Moshi.Builder().build().adapter(Result::class.java).toJson(result)
            viewModel.callAnalytics(response)
        }

        //recycler
        dataBinding.rvStarwarsPeopleList.adapter = StarwarsPeopleListAdapter(rvClickAction, favoriteClickAction)

        //Searchview
        dataBinding.svStarwarsPeopleList.setOnQueryTextListener(object : OnQueryTextListener,
            SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.receivedSearch.value = newText
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.start(query, true)
                flagSearchSummit = true
                return true
            }
        })
    }

    private fun setupListeners() {
        //recycler observe
        viewModel.dataReceived.observe(viewLifecycleOwner, Observer {
            val starwarsPeopleListAdapter = rv_starwars_people_list.adapter as StarwarsPeopleListAdapter
            starwarsPeopleListAdapter.submitList(it)

        })

        //search observe
        viewModel.receivedSearch.observe(viewLifecycleOwner, Observer {
            if (flagSearchSummit && it.isEmpty()) {
                flagSearchSummit = false
                viewModel.start("", true)
            }
            if (it.isEmpty()) { dataBinding.svStarwarsPeopleList.setQuery(it, false) }
        })

        //endless scroll
        rv_starwars_people_list.loadMore { viewModel.start(viewModel.receivedSearch.value!!) }
    }

}
