package com.bbrustol.maytheforcebewithbruno.presentation.list_navigation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bbrustol.maytheforcebewithbruno.R
import com.bbrustol.maytheforcebewithbruno.data.entity.Result
import com.bbrustol.maytheforcebewithbruno.data.infraestructure.DataMock.Companion.emptyResultModel
import com.bbrustol.maytheforcebewithbruno.databinding.FragmentStarwarsDetailBinding
import com.bbrustol.maytheforcebewithbruno.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class StarwarsPeopleDetailFragment : Fragment() {

    private val viewModel: StarwarsPeopleDetailViewModel by viewModel()

    private lateinit var dataBinding: FragmentStarwarsDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_starwars_detail, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            super.onSaveInstanceState(savedInstanceState)
        }
        dataBinding.lifecycleOwner = this
        dataBinding.executePendingBindings()

        arguments?.let { bundle -> initializeRecyclerView(bundle.getParcelable(Constants.EXTRA_STARWARS_PEOPLE_SELECTED_RESULT) ?: emptyResultModel()) }

    }

    private fun initializeRecyclerView(result: Result) {

        val adapter = StarwarsPeopleDetailAdapter()
        dataBinding.rvStarwarsPeopleDetail.adapter = adapter
        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) { 2 } else { 1 }
            }
        }

        dataBinding.rvStarwarsPeopleDetail.layoutManager = gridLayoutManager

        adapter.update(viewModel.format(result))
    }
}
