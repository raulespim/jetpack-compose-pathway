package com.example.marsphotos.data

import com.example.marsphotos.network.MarsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * Manual Dependency Injection
 *
 * By using dependency injection, it is easier to test the ViewModel.
 * Your app is now more flexible, robust, and ready to scale.
 */

interface AppContainer {
    val marsPhotosRepository: MarsPhotosRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl =
        "https://android-kotlin-fun-mars-server.appspot.com/"

    val contentType = "application/json".toMediaType()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory(contentType))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

    override val marsPhotosRepository: MarsPhotosRepository by lazy {
        NetworkMarsPhotosRepository(retrofitService)
    }

}