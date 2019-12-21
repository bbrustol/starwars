package com.bbrustol.maytheforcebewithbruno.presentation.list_navigation.starwarsPeopleList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bbrustol.maytheforcebewithbruno.R
import com.bbrustol.maytheforcebewithbruno.data.entity.Result
import com.bbrustol.maytheforcebewithbruno.databinding.ItemCardStarwarsListBinding


class StarwarsPeopleListAdapter(
    val onItemRvClickAction: (Result) -> Unit,
    val onItemfavoriteClickAction: (Result) -> Unit
) : ListAdapter<Result, StarwarsPeopleListAdapter.StarwarsPeopleListViewHandler>(
    StarwarsPeopleListDiffCallback()
) {

    private var flagClick = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarwarsPeopleListViewHandler {

        val layoutInflater = LayoutInflater.from(parent.context)

        val itemBinding = inflate<ItemCardStarwarsListBinding>(
            layoutInflater,
            R.layout.item_card_starwars_list, parent, false
        )

        return StarwarsPeopleListViewHandler(itemBinding)
    }

    override fun onBindViewHolder(holder: StarwarsPeopleListViewHandler, position: Int) = holder.bind(getItem(position))

    inner class StarwarsPeopleListViewHandler(private val binding: ItemCardStarwarsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result) = with(binding) {
            item = result
            isFavCheck = false
            root.setOnClickListener { onItemRvClickAction(result) }


            imgbtnFavorite.setOnClickListener {
                onItemfavoriteClickAction(result)
                flagClick = !flagClick
                isFavCheck = flagClick

            }
            executePendingBindings()
        }
    }

    class StarwarsPeopleListDiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }
}