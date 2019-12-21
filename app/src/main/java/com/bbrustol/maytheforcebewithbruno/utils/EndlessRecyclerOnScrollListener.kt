package com.bbrustol.maytheforcebewithbruno.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class EndlessRecyclerOnScrollListener(val onLoadMore: () -> Unit) : RecyclerView.OnScrollListener() {

    private var mPreviousTotal = 1
    private var mLoading = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

        if (recyclerView.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
            return
        }

        val totalItemCount = recyclerView.layoutManager?.itemCount
        val firstVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

        if (mLoading) {
            if (totalItemCount!! > mPreviousTotal) {
                mLoading = false
                mPreviousTotal = totalItemCount
            }
        }

        val visibleThreshold = 1
        if (!mLoading && totalItemCount!! <= firstVisibleItem + visibleThreshold) {
            // End has been reached

            onLoadMore()

            mLoading = true
        }
    }
}
