package com.chipcerio.newsly.features.list

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.chipcerio.newsly.App
import com.chipcerio.newsly.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var viewModel: TopHeadlinesViewModel

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appComponent().inject(this)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        val d = viewModel.loadTopHeadlines("bbc-news")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Timber.d("size: ${it.size}") }, { Timber.e(it) })
        disposables.add(d)
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }
}
