package com.bbrustol.maytheforcebewithbruno.business

import android.provider.MediaStore.Video.VideoColumns.CATEGORY
import com.bbrustol.maytheforcebewithbruno.data.infraestructure.*
import com.bbrustol.maytheforcebewithbruno.data.provider.StarwarsListProvider
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test


class StarwarsListBusinessTest {

    private lateinit var business: StarwarsBusiness

    @MockK(relaxUnitFun = true)
    private lateinit var remoteProvider: StarwarsListProvider

    private val response = DataMock.createList()

    private val result = DataMock.emptyResultModel()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        business = StarwarsBusiness(remoteProvider)
    }

    @Test
    fun shouldBeRetrieveFromRemote() = runBlocking {

        coEvery { remoteProvider.fetchStarwarsPeopleList(any(), any()) } returns Success(response)

        val fetch = business.fetchStarwarswPeoplelist(0, "")

        fetch.handle({ assertEquals(response, data) })
    }


    @Test
    fun shouldBeErrorFromRemote() = runBlocking {

        coEvery { remoteProvider.fetchStarwarsPeopleList(any(), any()) } returns Failure(Error(CATEGORY))

        val fetch = business.fetchStarwarswPeoplelist(0, "")

        fetch.handle({ fail() }, { assertEquals(CATEGORY, data.message) })
    }

    @Test
    fun shouldBeRetrieveResult() = runBlocking {

        coEvery { remoteProvider.fetchStarwarsPeopleList(any(), any()) } returns Success(response)

        val fetch = business.fetchStarwarswPeoplelist(0, "")

        fetch.handle({
            assertEquals(result, data.results[0])
            assertEquals(response.results[0], data.results[0])
        })
    }

    @Test
    fun shouldBeRetrieveErrorList() = runBlocking {

        coEvery { remoteProvider.fetchStarwarsPeopleList(any(), any()) } returns Failure(
            Error(
                DataMock.ILLEGAL_ARGUMENT
            )
        )

        val fetch = business.fetchStarwarswPeoplelist(0, "")

        fetch.handle({ fail() }, { assertEquals(DataMock.ILLEGAL_ARGUMENT, data.message) })
    }

    @Test
    fun shouldBeRetrieveErrorWhenFetchList() = runBlocking {

        coEvery { remoteProvider.fetchStarwarsPeopleList(any(), any()) } returns Failure(
            Error(
                DataMock.ILLEGAL_ARGUMENT
            )
        )

        val fetch = business.fetchStarwarswPeoplelist(0, "")

        fetch.handle({ fail() }, { assertEquals(DataNotAvailable().javaClass, this.networkState.javaClass) })
    }
}