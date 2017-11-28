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
import kotlinx.android.synthetic.main.activity_main.recyclerView
import timber.log.Timber
import javax.inject.Inject

class ArticlesActivity : AppCompatActivity(), ArticlesAdapter.OnArticleClickListener {

//    @Inject lateinit var viewModel: TopHeadlinesViewModel

    @Inject lateinit var viewModel: EverythingViewModel

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appComponent().inject(this)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private val paginator = PublishSubject.create<Int>()

    override fun onStart() {
        super.onStart()
        val d = viewModel.loadArticles(arrayListOf("bbc-news", "abc-news"), 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ setArticles(it) }, { Timber.e(it) })
        disposables.add(d)
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    private fun setArticles(articles: List<Article>) {
        val adapter = ArticlesAdapter(articles, this)
        recyclerView.adapter = adapter
    }

    override fun onArticleClick(article: Article) {
        Timber.d("$article")
//        val intent = Intent(this, DetailsActivity::class.java)
//        intent.putExtra(EXTRA_ARTICLE, article)
//        startActivity(intent)
    }
}
