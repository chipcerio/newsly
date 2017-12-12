package com.chipcerio.newsly.features.list

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.chipcerio.newsly.App
import com.chipcerio.newsly.R
import com.chipcerio.newsly.data.Article
import com.chipcerio.newsly.features.details.DetailsActivity
import com.chipcerio.newsly.features.details.DetailsActivity.Companion.EXTRA_ARTICLE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import timber.log.Timber
import javax.inject.Inject

class ArticlesActivity : AppCompatActivity(),
        ArticlesAdapter.OnArticleClickListener,
        ArticlesAdapter.OnLoadMoreItemsListener {

    @Inject lateinit var viewModel: EverythingViewModel

    private lateinit var adapter: ArticlesAdapter

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appComponent().inject(this)
        setContentView(R.layout.activity_main)

        toolbarView.title = getString(R.string.app_name)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ArticlesAdapter(mutableListOf(), this, this)
        recyclerView.adapter = adapter
    }

    private val paginate = PublishSubject.create<Int>()

    private var page = 1

    override fun onStart() {
        super.onStart()
        // https://stackoverflow.com/a/29594194/1076574
        // https://stackoverflow.com/a/35554835/1076574
        paginate.observeOn(Schedulers.io())
                .concatMap {
                    viewModel.loadArticles(arrayListOf("bbc-news", "abc-news"), it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("subscribe, thread: ${Thread.currentThread().id}")
                    setArticles(it)
                }, { Timber.e(it) })

        paginate.onNext(page)
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    private fun setArticles(articles: List<Article>) {
        articles.forEach {
            Timber.d(it.title)
            adapter.add(articles.indexOf(it), it)
        }
        page += 1
    }

    override fun onArticleClick(article: Article) {
        Timber.d("$article")
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(EXTRA_ARTICLE, article)
        startActivity(intent)
    }

    override fun onLoadMoreItems() {
        Timber.d("onLoadMoreItems on page $page")
        paginate.onNext(page)
    }
}
