package com.bbrustol.maytheforcebewithbruno.presentation.list_navigation.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.recyclerview.widget.RecyclerView
import com.bbrustol.maytheforcebewithbruno.R
import com.bbrustol.maytheforcebewithbruno.data.entity.ResultDetails
import com.bbrustol.maytheforcebewithbruno.databinding.ItemCardStarwarsDetailBinding
import com.bbrustol.maytheforcebewithbruno.databinding.ItemCardStarwarsDetailHeaderBinding


class StarwarsPeopleDetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var myContext: Context
    private lateinit var updatesList: ArrayList<ResultDetails>
    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_VALUE = 1
    }

    override fun getItemCount(): Int = updatesList.size

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_HEADER
        } else {
            TYPE_VALUE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        myContext = parent.context

        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            TYPE_HEADER -> {
                HeaderViewHolder(inflate(
                    layoutInflater,
                    R.layout.item_card_starwars_detail_header, parent, false
                ))
            }
            TYPE_VALUE -> {
                ValueViewHolder(inflate(
                    layoutInflater,
                    R.layout.item_card_starwars_detail, parent, false
                ))
            } else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind(updatesList[position])
            is ValueViewHolder -> holder.bind(updatesList[position])
            else -> throw IllegalArgumentException()
        }
    }

    fun update(update: ArrayList<ResultDetails>) {
        updatesList = update
    }

    inner class HeaderViewHolder(private val binding: ItemCardStarwarsDetailHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(response: ResultDetails) = with(binding) {
            title = response.value
            executePendingBindings()
        }
    }

    inner class ValueViewHolder(private val binding: ItemCardStarwarsDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(response: ResultDetails) = with(binding) {
            title = response.key
            value = response.value
            executePendingBindings()
        }
    }
}