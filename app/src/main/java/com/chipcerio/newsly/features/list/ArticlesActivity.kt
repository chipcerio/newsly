package com.chipcerio.newsly.features.list

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.chipcerio.newsly.R
import com.chipcerio.newsly.api.ArticleDeserializer
import com.chipcerio.newsly.api.ArticlesResponse
import com.chipcerio.newsly.api.SourceDeserializer
import com.chipcerio.newsly.common.ext.toast
import com.chipcerio.newsly.data.raw_types.Article
import com.chipcerio.newsly.data.raw_types.Source
import com.chipcerio.newsly.features.details.DetailsActivity
import com.chipcerio.newsly.features.details.DetailsActivity.Companion.EXTRA_ARTICLE
import com.chipcerio.newsly.features.list.ArticlesAdapter.OnArticleClickListener
import com.chipcerio.newsly.features.list.ArticlesAdapter.OnLoadMoreItemsListener
import com.google.gson.GsonBuilder
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import timber.log.Timber
import javax.inject.Inject

class ArticlesActivity : DaggerAppCompatActivity(), OnArticleClickListener, OnLoadMoreItemsListener {

    @Inject
    lateinit var viewModel: ArticlesViewModel

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

        jsonTest()
    }

    private fun jsonTest() {
        val json = """
{
  "status": "ok",
  "totalResults": 3,
  "articles": [
    {
      "source": {
        "id": "reuters",
        "name": "Reuters"
      },
      "author": "Reuters Editorial",
      "title": "Sanofi set to buy Bioverativ for more than USD11.5 billion: WSJ",
      "description": "Sanofi SA is close to a deal to take over U.S. drug maker Bioverativ Inc for more than USD11.5 billion, the Wall Street Journal reported, citing people familiar with the matter.",
      "url": "https://www.reuters.com/article/us-bioverativ-m-a-sanofi-fr/sanofi-set-to-buy-bioverativ-for-more-than-11-5-billion-wsj-idUSKBN1FB05Y",
      "urlToImage": "https://s2.reutersmedia.net/resources/r/?m=02&d=20180122&t=2&i=1224016770&w=1200&r=LYNXMPEE0L03G",
      "publishedAt": "2018-01-22T02:23:35Z"
    },
    {
      "source": {
        "id": null,
        "name": "Ledgergazette.com"
      },
      "author": "Joyce Ramirez",
      "title": "Chicago Bridge & Iron (CBI) Shares Bought by Legal & General Group Plc",
      "description": "Legal & General Group Plc increased its position in shares of Chicago Bridge & Iron (NYSE:CBI) by 23.4% in the 3rd quarter, according to the company in its most recent Form 13F filing with the Securities and Exchange Commission. The institutional investor own…",
      "url": "https://ledgergazette.com/2018/01/22/legal-general-group-plc-acquires-23238-shares-of-chicago-bridge-iron-cbi.html",
      "urlToImage": "https://www.marketbeat.com/logos/chicago-bridge--iron-company-logo.jpg",
      "publishedAt": "2018-01-22T00:59:48Z"
    },
    {
      "source": {
        "id": null,
        "name": "Venturebeat.com"
      },
      "author": "Cosette Jarrett",
      "title": "Amazon set to open doors on AI-powered grocery store",
      "description": "As the rest of us wait in checkout lines during our Sunday evening grocery shopping, folks in Seattle eagerly await tomorrow's public opening of the first store to eliminate the need for cashiers. The much-anticipated Amazon Go grocery store will open its doo…",
      "url": "https://venturebeat.com/2018/01/21/amazon-set-to-open-doors-on-ai-powered-grocery-store/",
      "urlToImage": "https://venturebeat.com/wp-content/uploads/2018/01/amazon-go-e1516568130237.jpg?fit=780%2C493&strip=all",
      "publishedAt": "2018-01-22T00:55:43Z"
    }
  ]
}
            """

        val gson = GsonBuilder()
            .registerTypeAdapter(Source::class.java, SourceDeserializer())
            .registerTypeAdapter(Article::class.java, ArticleDeserializer())
            .setLenient()
            .create()

        val response = gson.fromJson(json, ArticlesResponse::class.java)

        Timber.d("${response.totalResults}")
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
        if (showing) toast(getString(R.string.loading_msg_fetching))
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
