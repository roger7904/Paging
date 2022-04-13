package com.roger.paging.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.roger.paging.model.entity.PeopleList
import com.roger.paging.model.network.PeopleApiService
import com.roger.paging.model.network.PeoplePagingSource
import com.roger.paging.model.repository.PeopleInfoRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainViewModel : ViewModel() {

    private val pagingSource =
        PeoplePagingSource(
            PeopleApiService(),
        )

    private val repository =
        PeopleInfoRepository(
            peoplePagingSource = pagingSource
        )

    private val compositeDisposable = CompositeDisposable()

    private val _peoplePagingData =
        MutableLiveData<PagingData<PeopleList.People>>()
    val peoplePagingData: LiveData<PagingData<PeopleList.People>> =
        _peoplePagingData

    init {
        getPeopleInfoPagingData()
    }

    fun getPeopleInfoPagingData() {
        compositeDisposable.add(
            repository.getPeopleInfo()
                .cachedIn(viewModelScope) //緩存Flowable<PagingData>
                .subscribe {
                    _peoplePagingData.postValue(it)
                }
        )
    }
}