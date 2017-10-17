package com.custview.balvier.customview.restservices

import com.custview.balvier.customview.ApplicationClass
import okhttp3.Cache
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient


/**
 * Created by nithin on 17/10/17.
 */
class RetrofitClient {
    companion object {
        private var retrofit: Retrofit? = null
        fun getRetrofitClient(cache: Cache): Retrofit {
            if (retrofit == null) {

                val okHttpClient = OkHttpClient.Builder()
                        .cache(cache)
                        .build()

                retrofit = Retrofit.Builder()
                        .baseUrl(ApplicationClass.BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

            }
            return retrofit!!
        }
    }
}