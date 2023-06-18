package org.d3if0108.piramidcount.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if0108.piramidcount.model.Penemu
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface PenemuApi {
    @GET("penemu.json")
    suspend fun getBiak(): List<Penemu>
}

object ServiceAPI {
    private const val BASE_URL_PENEMU = "https://raw.githubusercontent.com/" +
            "rifanmuhammadhidayat/rifan-resolusi/dataAPI/"
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofitPenemu = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL_PENEMU)
        .build()

    //Retrofit Create


    val penemuService: PenemuApi by lazy {
        retrofitPenemu.create(PenemuApi::class.java)
    }

    //Function


    fun getPenemuUrl(imageId: String): String {
        return "$BASE_URL_PENEMU$imageId.jpg"
    }
}


enum class ApiStatus { LOADING, SUCCES,FAILED}