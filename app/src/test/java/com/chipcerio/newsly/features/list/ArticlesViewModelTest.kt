package com.chipcerio.newsly.features.list

import com.chipcerio.newsly.data.dto.Article
import com.chipcerio.newsly.data.dto.Source
import com.chipcerio.newsly.data.source.repository.ArticlesRepository
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit

// http://www.baeldung.com/rxjava-testing
class ArticlesViewModelTest {

    @Rule @JvmField
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var repository: ArticlesRepository

    private lateinit var viewModel: ArticlesViewModel

    private lateinit var SOURCES: ArrayList<String>

    private lateinit var ARTICLES: ArrayList<Article>

    private lateinit var NO_ARTICLES: ArrayList<Article>

    private lateinit var testObserver: TestObserver<List<Article>>

    private val PAGE_1 = 1

    @Before
    fun setupArticlesViewModel() {
        MockitoAnnotations.initMocks(this)

        viewModel = ArticlesViewModel(repository)

        SOURCES = arrayListOf("abc-news", "bbc-news")

        NO_ARTICLES = arrayListOf()

        ARTICLES = arrayListOf(
            Article(
                id = 0L,
                source = Source("abc_news", "ABC News","", "", "", "", ""),
                author = "ABC News",
                title = "UK News",
                description = "long description",
                url = "http://url.com",
                urlToImage = "http://url.com/image.png",
                publishedAt = "Dec 05, 2017"),
            Article(
                id = 1L,
                source = Source("bbc_news", "BBC News", "", "", "", "", ""),
                author = "BBC News",
                title = "London News",
                description = "long description",
                url = "http://url.com",
                urlToImage = "http://url.com/image.png",
                publishedAt = "Dec 06, 2017"))

        testObserver = TestObserver.create()
    }

    @Test
    fun should_HaveArticles_When_Requested() {
        // given
        `when`(repository.getArticles(SOURCES, PAGE_1))
            .thenReturn(Observable.just(ARTICLES))

        // when
        viewModel.loadArticles(SOURCES, PAGE_1)
            .subscribe(testObserver)

        // then
        testObserver.assertSubscribed()
        testObserver.assertResult(ARTICLES)
        assertFalse(ARTICLES.isEmpty())
    }

    @Test
    fun Should_DisplayNothing_When_ArticlesIsEmpty() {
        `when`(repository.getArticles(SOURCES, PAGE_1))
            .thenReturn(Observable.just(NO_ARTICLES))

        viewModel.loadArticles(SOURCES, PAGE_1)
            .subscribe(testObserver)

        testObserver.assertSubscribed()
        assertTrue(NO_ARTICLES.isEmpty())
    }
}