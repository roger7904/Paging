package com.roger.paging.model.network

import com.roger.paging.model.entity.PeopleList
import com.roger.paging.utils.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET(Constants.API_ENDPOINT)
    fun getInfo(
        @Query(Constants.PAGE) page: Int,
    ): Single<PeopleList>

}