package com.chipcerio.newsly.features.sources_list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.chipcerio.newsly.R
import com.chipcerio.newsly.data.dto.Source
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_sources.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class SourcesActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: SourcesViewModel

    private lateinit var adapter: SourcesAdapter

    private lateinit var disposables: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sources)

        toolbarView.title = getString(R.string.title_sources)
        setSupportActionBar(toolbarView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        adapter = SourcesAdapter(mutableListOf())

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SourcesActivity)
            adapter = this@SourcesActivity.adapter
        }
    }

    override fun onStart() {
        super.onStart()
        disposables = CompositeDisposable()
        disposables.add(
            viewModel.getSources()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    mapSourcesToView(it)
                }
                .subscribe()
        )
    }

    private fun mapSourcesToView(sources: List<Source>) {
        sources.forEach {
            adapter.add(sources.indexOf(it), it)
        }
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}