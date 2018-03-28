package com.chipcerio.newsly.features.sources_list

import com.chipcerio.newsly.data.dto.Source
import com.chipcerio.newsly.data.source.repository.SourcesRepository
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit

class SourcesViewModelTest {

    @Rule @JvmField
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var repository: SourcesRepository

    private lateinit var viewModel: SourcesViewModel

    private lateinit var SOURCES: ArrayList<Source>

    private lateinit var testObserver: TestObserver<List<Source>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = SourcesViewModel(repository)

        SOURCES = arrayListOf(
            Source(
                id = "abc_news",
                name = "ABC News",
                description = "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
                url = "http://abcnews.go.com",
                category = "general",
                language = "en",
                country = "us"
            ),
            Source(
                id = "ansa",
                name = "ANSA.it",
                description = "Agenzia ANSA: ultime notizie, foto, video e approfondimenti su: cronaca, politica, economia, regioni, mondo, sport, calcio, cultura e tecnologia.",
                url = "http://www.ansa.it",
                category = "general",
                language = "it",
                country = "it"
            )
        )

        testObserver = TestObserver.create()
    }

    @Test
    fun should_HaveSources_When_Requested() {
        // given
        `when`(repository.getSources()).thenReturn(Observable.just(SOURCES))

        // when
        viewModel.getSources().subscribe(testObserver)

        // then
        testObserver.assertSubscribed()
        testObserver.assertResult(SOURCES)
    }
}