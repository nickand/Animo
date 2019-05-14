package com.nosti.animo.network

import com.nosti.animo.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AnimeApiClient {

    companion object {
        private var retrofit: Retrofit? = null

        fun getClient(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }

        private fun getHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BASIC
            return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor { chain ->
                    var request = chain.request()
                    request = request.newBuilder().build()
                    chain.proceed(request)
                }.build()
        }
    }
}