package com.roger.paging.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.roger.paging.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val pagingDataAdaptor = PeopleInfoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.run {
            swipeRefresh.setOnRefreshListener {
                viewModel.getFactoryInfoPagingData()
            }

            mBinding.rvInfo.apply {
                adapter = pagingDataAdaptor.withLoadStateFooter(
                    footer = FooterLoadStateAdapter {
                        pagingDataAdaptor.retry()
                    })
            }

            pagingDataAdaptor.addLoadStateListener { loadState ->
                //show progress bar when the load state is Loading
                swipeRefresh.isRefreshing = loadState.refresh is LoadState.Loading
                //load state for error and show the msg on UI
                llError.isVisible = loadState.refresh is LoadState.Error
                rvInfo.isVisible = loadState.refresh is LoadState.NotLoading

                if (loadState.refresh is LoadState.Error) {
                    btnRetry.setOnClickListener {
                        pagingDataAdaptor.retry() //paging library 會觸發 pagingSource.load()
                    }

                    val errorMessage = (loadState.refresh as LoadState.Error).error.message
                    tvErrorMessage.text = errorMessage
                }
            }

            viewModel.peoplePagingData.observe(this@MainActivity) {
                pagingDataAdaptor.submitData(lifecycle, it)
            }
        }
    }
}