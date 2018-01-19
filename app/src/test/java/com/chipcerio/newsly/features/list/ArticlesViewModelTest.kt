package com.chipcerio.newsly.features.list

import com.chipcerio.newsly.data.raw_types.Article
import com.chipcerio.newsly.data.raw_types.Source
import com.chipcerio.newsly.data.source.repository.ArticlesRepository
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit

class ArticlesViewModelTest {

    @Rule @JvmField
    val mockitoRule = MockitoJUnit.rule()

    @Mock private lateinit var repository: ArticlesRepository

    private lateinit var viewModel: ArticlesViewModel

    private lateinit var SOURCES: ArrayList<String>

    private lateinit var ARTICLES: ArrayList<Article>

    private lateinit var testObserver: TestObserver<List<Article>>

    private val PAGE_1 = 1

    @Before
    fun setupArticlesViewModel() {
        MockitoAnnotations.initMocks(this)

        viewModel = ArticlesViewModel(repository)

        SOURCES = arrayListOf("abc-news", "bbc-news")

        ARTICLES = arrayListOf(
            Article(
                source = Source("", ""),
                author = "ABC News",
                title = "UK News",
                description = "long desription",
                url = "http://url.com",
                urlToImage = "http://url.com/image.png",
                publishedAt = "Dec 05, 2017"),
            Article(
                source = Source("", ""),
                author = "BBC News",
                title = "London News",
                description = "long desription",
                url = "http://url.com",
                urlToImage = "http://url.com/image.png",
                publishedAt = "Dec 06, 2017"))

        testObserver = TestObserver.create()
    }

    @Test
    fun Should_HaveArticles_When_Requested() {
        // given
        `when`(repository.getArticles(SOURCES, PAGE_1)).thenReturn(Observable.just(ARTICLES))

        // when
        viewModel.loadArticles(SOURCES, PAGE_1).subscribe(testObserver)

        // then
        testObserver.assertSubscribed()
        testObserver.assertResult(ARTICLES)
    }
}