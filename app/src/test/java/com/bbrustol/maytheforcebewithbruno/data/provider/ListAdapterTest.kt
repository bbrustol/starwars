package com.bbrustol.maytheforcebewithbruno.data.provider

import com.bbrustol.maytheforcebewithbruno.data.entity.StarwarsPeopleResponse
import com.bbrustol.maytheforcebewithbruno.data.infraestructure.ResourceUtils
import com.squareup.moshi.Moshi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class ListAdapterTest {

    private lateinit var moshi: Moshi

    @Before
    fun setUp(){
        moshi = Moshi.Builder()
            .build()
    }

    @Test
    fun shouldBeParse() {
        val json = ResourceUtils().openFile("list_response_200.json")


        val adapter =
            moshi.adapter(StarwarsPeopleResponse::class.java)
        val response = adapter.fromJson(json)

        val result = response?.results?.get(0)

        assertNotNull(result)

        assertEquals("Luke Skywalker", result?.name)
        //explicit null value
        assertEquals("172", result?.height)

        assertEquals("19BBY", result?.birth_year)

        assertEquals(null, response?.previous)

        assertTrue(response?.results?.size!! > 0)
    }

    @Test
    fun shouldBeReturnEmptyList() {
        val json = ResourceUtils().openFile("list_response_empty.json")
        val adapter =
            moshi.adapter(StarwarsPeopleResponse::class.java)
        val response= adapter.fromJson(json)

        assertTrue(response?.next.isNullOrEmpty())
        assertEquals(response?.count, 0)
        assertTrue(response?.previous.isNullOrEmpty())
        assertTrue(response?.results.isNullOrEmpty())
    }
}