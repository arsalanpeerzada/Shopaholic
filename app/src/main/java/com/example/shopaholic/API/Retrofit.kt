package com.mrusmansiddique.gpt_android.api


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import retrofit2.Retrofit

class Retrofit {

    companion object {

        private val interceptor: HttpLoggingInterceptor
            get() {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                return interceptor
            }

        private val client: OkHttpClient
            get() {
                try {

                    val builder = OkHttpClient.Builder()

                    builder.addInterceptor(interceptor)
                    builder.connectTimeout(1, TimeUnit.MINUTES)
                        .writeTimeout(45, TimeUnit.SECONDS)
                        .readTimeout(35, TimeUnit.SECONDS)
                    return builder.build()
                } catch (e: Exception) {
                    throw RuntimeException(e)
                }
            }

        private var retrofit: Retrofit? = null

        private val BASE_URL = Server.BASE_URL

        val instance: Retrofit?
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build()
                }

                return retrofit
            }

    }


}