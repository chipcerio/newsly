package com.chipcerio.newsly.features.list

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.chipcerio.newsly.App
import com.chipcerio.newsly.R
import com.chipcerio.newsly.data.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.recyclerView
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ArticlesAdapter.OnArticleClickListener {

    @Inject lateinit var viewModel: TopHeadlinesViewModel

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appComponent().inject(this)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        val d = viewModel.loadTopHeadlines("bbc-news")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ setArticles(it) }, { Timber.e(it) })
        disposables.add(d)
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    private fun setArticles(articles: MutableList<Article>) {
        val adapter = ArticlesAdapter(articles, this)
        recyclerView.adapter = adapter
    }

    override fun onArticleClick(article: Article) {
        Timber.d("$article")
    }
}
