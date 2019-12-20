package com.bbrustol.maytheforcebewithbruno.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bbrustol.maytheforcebewithbruno.business.StarwarsBusiness
import com.bbrustol.maytheforcebewithbruno.data.entity.Result
import com.bbrustol.maytheforcebewithbruno.data.infraestructure.DataMock
import com.bbrustol.maytheforcebewithbruno.data.infraestructure.Success
import com.bbrustol.maytheforcebewithbruno.data.infraestructure.UI
import com.bbrustol.maytheforcebewithbruno.presentation.list_navigation.starwarsPeopleList.StarwarsPeopleListViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ListViewModelTest {

    @MockK(relaxUnitFun = true)
    private lateinit var business: StarwarsBusiness

    @MockK(relaxUnitFun = true)
    private lateinit var observerSuccess: Observer<List<Result>>

    @MockK(relaxUnitFun = true)
    private lateinit var application: StarwarsApplication

    private val response = DataMock.createList()

    private lateinit var viewModel : StarwarsPeopleListViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = StarwarsPeopleListViewModel(business, application, UI)
        every { application.getString(any()) } returns " "
    }

    @Test
    fun shouldBeRetriveFirtAccessWithSuccess() {

        viewModel.dataReceived.value = null

        viewModel.dataReceived.observeForever(observerSuccess)

        coEvery { business.fetchStarwarswPeoplelist(any()) } returns Success(response)

        viewModel.start()

        verify(exactly = 1) { observerSuccess.onChanged(response.results) }
    }
}

