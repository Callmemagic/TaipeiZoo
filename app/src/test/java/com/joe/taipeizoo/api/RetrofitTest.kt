package com.joe.taipeizoo.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.joe.taipeizoo.APIInterface.APIInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStreamReader

/**
 * author: Joe Cheng
 */
@RunWith(JUnit4::class)
class RetrofitTest {
    @get:Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    var testDispatcher = TestCoroutineDispatcher()

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiInterface: APIInterface

    @Before
    fun createService() {
        Dispatchers.setMain(testDispatcher)
        mockWebServer = MockWebServer()
        mockWebServer.start()
        apiInterface = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(APIInterface::class.java)
    }

    @After
    fun stopService() {
        testDispatcher.cleanupTestCoroutines()
        mockWebServer.shutdown()
    }


    @Test
    fun `read field success json file`(){
        val reader = MockResponseFileReader("api_response/field.json")
        assertNotNull(reader.content)
    }

    @Test
    fun `read animal success json file`(){
        val reader = MockResponseFileReader("api_response/animal.json")
        assertNotNull(reader.content)
    }

    @Test
    fun `can get field list result` () = runBlocking {
        withContext(Dispatchers.Main) {
            enqueueResponse("field.json")
            val resp = apiInterface.fieldInfo.await()
            assertNotNull(resp)
            assertThat(resp.result.count, `is`(15))
            assertThat(resp.result.results.size, `is`(15))
        }
    }

    @Test
    fun `can get animal list result` () = runBlocking {
        withContext(Dispatchers.Main) {
            enqueueResponse("animal.json")
            val resp = apiInterface.animalInfo.await()
            assertNotNull(resp)
            assertThat(resp.result.count, `is`(15))
            assertThat(resp.result.results.size, `is`(15))
        }
    }

    private fun enqueueResponse(fileName : String) {
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream("api_response/$fileName"))
        var content = reader.readText()
        val mockResponse = MockResponse()
        mockWebServer.enqueue(mockResponse.setBody(content))

        reader.close()
    }

    class MockResponseFileReader(path: String) {

        val content: String

        init {
            val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(path))
            content = reader.readText()
            reader.close()
        }
    }
}