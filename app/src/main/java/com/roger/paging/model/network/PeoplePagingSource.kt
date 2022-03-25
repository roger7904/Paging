package com.roger.paging.model.network

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.roger.paging.model.entity.PeopleList
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException


class PeoplePagingSource(
    private val peopleApiService: PeopleApiService
) : RxPagingSource<Int, PeopleList.People>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, PeopleList.People>> {
        val position = params.key ?: 1
        return peopleApiService
            .getInfo(position)
            .subscribeOn(Schedulers.io())
            .map<LoadResult<Int, PeopleList.People>> { result ->
                LoadResult.Page(
                    data = result.responseData,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (position == result.totalPage.toInt()) null else position + 1
                )
            }
            .onErrorReturn { e ->
                when (e) {
                    is IOException -> LoadResult.Error(e)
                    is HttpException -> LoadResult.Error(e)
                    else -> throw e
                }
            }
    }

    //提供頁面更新所要返回的key，如果key=null，則載入初始頁面
    override fun getRefreshKey(state: PagingState<Int, PeopleList.People>): Int? {
        return state.anchorPosition
    }
}

