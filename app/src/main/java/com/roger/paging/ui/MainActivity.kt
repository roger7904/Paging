package com.roger.paging.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.roger.paging.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val pagingDataAdaptor = PeopleInfoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.rvInfo.apply {
            adapter = pagingDataAdaptor
        }

        viewModel.peoplePagingData.observe(this) {
            pagingDataAdaptor.submitData(lifecycle, it)
        }
    }
}