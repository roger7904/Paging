package com.roger.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.roger.paging.model.entity.PeopleList
import com.roger.paging.model.network.ApiService
import com.roger.paging.utils.Constants
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val apiService = ApiService()
    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        compositeDisposable.add(
            apiService.getInfo(1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PeopleList>() {
                    override fun onSuccess(value: PeopleList?) {
                        Log.d("TAG", "onSuccess: $value")
                    }

                    override fun onError(e: Throwable?) {
                        e?.let {

                        }
                    }
                })
        )
    }
}