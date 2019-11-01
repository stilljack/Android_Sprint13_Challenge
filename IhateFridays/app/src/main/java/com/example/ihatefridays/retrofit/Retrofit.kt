package com.example.ihatefridays.retrofit

import com.example.ihatefridays.model.MakeUp
import com.google.gson.GsonBuilder
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface MakeupApiInterface {
    @GET ("products.json")
    fun searchMakeup(@Query("brand")query: String): Single<List<MakeUp>>
}

class RetrofitInstance {

    companion object {

        private const val  BASEURL = "http://makeup-api.herokuapp.com/api/v1/"


        private val retrofitService = Retrofit
            .Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MakeupApiInterface::class.java)
    }

    fun searchMakeup(query: String): Single<List<MakeUp>> {
        return retrofitService.searchMakeup(query)
    }
}