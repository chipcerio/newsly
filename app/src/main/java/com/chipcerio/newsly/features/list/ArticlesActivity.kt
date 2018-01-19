package com.chipcerio.newsly.features.list

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.chipcerio.newsly.R
import com.chipcerio.newsly.common.ext.toast
import com.chipcerio.newsly.data.raw_types.Article
import com.chipcerio.newsly.features.details.DetailsActivity
import com.chipcerio.newsly.features.details.DetailsActivity.Companion.EXTRA_ARTICLE
import com.chipcerio.newsly.features.list.ArticlesAdapter.OnArticleClickListener
import com.chipcerio.newsly.features.list.ArticlesAdapter.OnLoadMoreItemsListener
import dagger.android.DaggerActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import timber.log.Timber
import javax.inject.Inject

class ArticlesActivity : DaggerActivity(), OnArticleClickListener, OnLoadMoreItemsListener {

    @Inject lateinit var viewModel: ArticlesViewModel

    private lateinit var adapter: ArticlesAdapter

    private lateinit var disposables: CompositeDisposable

    private val paginate = PublishSubject.create<Int>()

    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarView.title = getString(R.string.app_name)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ArticlesAdapter(mutableListOf(), this, this)
        recyclerView.adapter = adapter

        disposables = CompositeDisposable()

        // https://stackoverflow.com/a/29594194/1076574
        // https://stackoverflow.com/a/35554835/1076574
        disposables.add(paginate.observeOn(Schedulers.io())
            .concatMap {
                viewModel.loadArticles(arrayListOf("bbc-news", "bloomberg"), it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ setArticles(it) }, { Timber.e(it) }))

        // putting in onCreate to prevent unwanted
        // network calls when switching activities
        paginate.onNext(page)
    }

    override fun onStart() {
        super.onStart()
        bindLoadingState()
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    private fun bindLoadingState() {
        disposables.add(viewModel.getLoadingIndicator()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ showLoadingIndicator(it) }))
    }

    private fun showLoadingIndicator(showing: Boolean) {
        if (showing) toast("Fetching articles...")
    }

    private fun setArticles(articles: List<Article>) {
        articles.forEach {
            Timber.d(it.title)
            adapter.add(articles.indexOf(it), it)
        }
        page += 1
    }

    override fun onArticleClick(article: Article) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(EXTRA_ARTICLE, article)
        startActivity(intent)
    }

    override fun onLoadMoreItems() = paginate.onNext(page)
}
