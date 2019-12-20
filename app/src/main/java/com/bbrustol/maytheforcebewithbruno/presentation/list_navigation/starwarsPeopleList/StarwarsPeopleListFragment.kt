package com.bbrustol.maytheforcebewithbruno.presentation.list_navigation.starwarsPeopleList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bbrustol.maytheforcebewithbruno.R
import com.bbrustol.maytheforcebewithbruno.data.entity.Result
import com.bbrustol.maytheforcebewithbruno.databinding.FragmentStarwarsListBinding
import com.bbrustol.maytheforcebewithbruno.utils.Constants
import com.bbrustol.maytheforcebewithbruno.utils.loadMore
import kotlinx.android.synthetic.main.fragment_starwars_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class StarwarsPeopleListFragment : Fragment() {

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
            viewModel.start()
            viewModel.flagFirstLoad.value = true
        }
    }

    private fun initializeRecyclerView(view: View) {
        dataBinding.rvStarwarsPeopleList.layoutManager = LinearLayoutManager(context)

        val clickAction = { result: Result ->
            val bundle = Bundle().apply { putParcelable(Constants.EXTRA_STARWARS_PEOPLE_SELECTED_RESULT, result) }
            view.findNavController().navigate(R.id.actionStarwarsListToDetail, bundle)
        }

        dataBinding.rvStarwarsPeopleList.adapter = StarwarsPeopleListAdapter(clickAction)
    }

    private fun setupListeners() {
        viewModel.dataReceived.observe(viewLifecycleOwner, Observer {
            val starwarsPeopleListAdapter = rv_starwars_people_list.adapter as StarwarsPeopleListAdapter
            starwarsPeopleListAdapter.submitList(it)
        })

        rv_starwars_people_list.loadMore { viewModel.start() }
    }
}
