package com.roger.paging.model.network

import com.roger.paging.model.entity.PeopleList
import com.roger.paging.utils.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ApiService {

    private val api = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(Api::class.java)

    fun getInfo(page : Int): Single<PeopleList> {
        return api.getInfo(
            page
        )
    }
}