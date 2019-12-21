package com.bbrustol.maytheforcebewithbruno.presentation.list_navigation.starwarsPeopleList

import android.app.Application
import android.view.View.GONE
import androidx.lifecycle.MutableLiveData
import com.bbrustol.maytheforcebewithbruno.business.StarwarsBusiness
import com.bbrustol.maytheforcebewithbruno.data.entity.Result
import com.bbrustol.maytheforcebewithbruno.data.entity.StarwarsPeopleResponse
import com.bbrustol.maytheforcebewithbruno.data.infraestructure.Failure
import com.bbrustol.maytheforcebewithbruno.data.infraestructure.Success
import com.bbrustol.maytheforcebewithbruno.data.infraestructure.handle
import com.bbrustol.maytheforcebewithbruno.presentation.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class StarwarsPeopleListViewModel(
    private val starwarsBusiness: StarwarsBusiness,
    application: Application,
    private val coroutineScope: CoroutineScope
) : BaseViewModel(application) {

    private var tempData: ArrayList<Result> = arrayListOf()
    private var offset = 1
    private var maxCount : Int = 11

    val dataReceived: MutableLiveData<List<Result>> = MutableLiveData()
    val listVisibility = MutableLiveData<Int>().apply { value = GONE }
    val flagFirstLoad = MutableLiveData<Boolean>().apply { value = false }
    var receivedSearch = MutableLiveData<String>().apply { value = "" }

    fun start(search: String, flagClean: Boolean = false) {
        if (flagClean || !flagFirstLoad.value!! || (offset * 10 < maxCount)) {

            configVisibility(ViewState.LOADING)
            receivedSearch.value = search
            if (flagClean) {
                offset = 1
                tempData = arrayListOf()
            }

            coroutineScope.launch {
                val resource = starwarsBusiness.fetchStarwarswPeoplelist(offset, search)
                resource.handle(success(), failure())
            }
        }
    }

    private fun failure(): Failure.() -> Unit = {
        configVisibility(ViewState.ERROR)
    }

    private fun success(): Success<StarwarsPeopleResponse>.() -> Unit = {
        if (!this.data.results.isNullOrEmpty()) {
            offset++

            for (index in this.data.results.indices) {
                tempData.add(this.data.results[index])
            }
            maxCount = this.data.count
            dataReceived.value = tempData

            configVisibility(ViewState.SUCCESS)
        } else if (offset == 0) {
            configVisibility(ViewState.NO_DATA)
        } else {
            configVisibility(ViewState.STOP_LOADING)
        }
    }

    override fun configVisibility(viewState: ViewState) {
        super.configVisibility(viewState)
        listVisibility.value = setupViewState(viewState).showData
    }

    override fun tryAgain() {
        start("", true)
    }

}