package com.roger.paging.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.roger.paging.model.entity.PeopleList
import com.roger.paging.model.network.PeoplePagingSource
import io.reactivex.rxjava3.core.Flowable

class PeopleInfoRepository(private val peoplePagingSource: PeoplePagingSource) {

    fun getFactoryInfo(): Flowable<PagingData<PeopleList.People>> {
        return Pager(
            config = PagingConfig(
                initialLoadSize = 30,
                pageSize = 15,
                prefetchDistance = 15,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                peoplePagingSource
            }
        ).flowable
    }
}