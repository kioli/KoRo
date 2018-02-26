package kioli.koro.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.subscribers.DisposableSubscriber
import kioli.koro.domain.interactor.ClearQuoteUseCase
import kioli.koro.domain.interactor.LoadQuoteUseCase
import kioli.koro.domain.interactor.SaveQuoteUseCase
import kioli.koro.domain.model.QuoteModelDomain
import kioli.koro.presentation.DataFactory
import kioli.koro.presentation.data.ResourceState
import kioli.koro.presentation.mapper.QuotePresentationMapper
import kioli.koro.presentation.model.QuoteModelPresentation
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import org.mockito.Mock

@RunWith(JUnit4::class)
class IntroViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var loadQuote: LoadQuoteUseCase
    @Mock
    private lateinit var saveQuote: SaveQuoteUseCase
    @Mock
    private lateinit var clearQuote: ClearQuoteUseCase
    @Mock
    private lateinit var mapper: QuotePresentationMapper

    @Captor
    private lateinit var captorDisposableSubscriber: KArgumentCaptor<DisposableSubscriber<QuoteModelDomain>>
    private lateinit var captorDisposableCompletableObserver: KArgumentCaptor<DisposableCompletableObserver>

    private lateinit var viewModel: IntroViewModel

    @Before
    fun setUp() {
        captorDisposableSubscriber = argumentCaptor()
        captorDisposableCompletableObserver = argumentCaptor()
        loadQuote = mock()
        saveQuote = mock()
        clearQuote = mock()
        mapper = mock()
        viewModel = IntroViewModel(loadQuote, saveQuote, clearQuote, mapper)
    }

    @Test
    fun loadQuoteExecutesUseCase() {
        // 2. Execute
        viewModel.loadQuote()
        // 3. Verify
        verify(loadQuote).execute(any(), anyOrNull())
    }

    @Test
    fun saveQuoteExecutesUseCase() {
        // 2. Execute
        viewModel.saveQuote("Test")
        // 3. Verify
        verify(saveQuote).execute(any(), anyOrNull())
    }

    @Test
    fun clearQuoteExecutesUseCase() {
        // 2. Execute
        viewModel.clearQuotes()
        // 3. Verify
        verify(clearQuote).execute(any(), anyOrNull())
    }

    @Test
    fun loadQuoteTriggersLoading() {
        // 2. Execute
        viewModel.loadQuote()
        // 3. Verify
        assert(viewModel.getLiveData().value?.status == ResourceState.LOADING)
        assert(viewModel.getLiveData().value?.data == null)
        assert(viewModel.getLiveData().value?.message == null)
    }

    @Test
    fun loadQuoteReturnsSuccess() {
        // 1.Prepare
        val quoteModelDomain = QuoteModelDomain(DataFactory.randomString())
        val quoteModelPresentation = QuoteModelPresentation(DataFactory.randomString())
        whenever(mapper.mapToPresentation(quoteModelDomain)).thenReturn(quoteModelPresentation)
        // 2. Execute
        viewModel.loadQuote()
        // 3. Verify
        verify(loadQuote).execute(captorDisposableSubscriber.capture(), eq(null))
        captorDisposableSubscriber.firstValue.onNext(quoteModelDomain)
        assert(viewModel.getLiveData().value?.status == ResourceState.SUCCESS)
        assert(viewModel.getLiveData().value?.data == quoteModelPresentation)
        assert(viewModel.getLiveData().value?.message == null)
    }

    @Test
    fun loadQuoteReturnsError() {
        // 1.Prepare
        val errorText = "test error"
        // 2. Execute
        viewModel.loadQuote()
        // 3. Verify
        verify(loadQuote).execute(captorDisposableSubscriber.capture(), eq(null))
        captorDisposableSubscriber.firstValue.onError(RuntimeException(errorText))
        assert(viewModel.getLiveData().value?.status == ResourceState.ERROR)
        assert(viewModel.getLiveData().value?.data == null)
        assert(viewModel.getLiveData().value?.message == errorText)
    }

    @Test
    fun saveQuoteDoesNotTriggerLoading() {
        // 1.Prepare
        val quoteModelDomain = QuoteModelDomain("quote domain model")
        whenever(mapper.mapFromPresentation(any())).thenReturn(quoteModelDomain)
        // 2. Execute
        viewModel.saveQuote("quote to save")
        // 3. Verify
        verify(saveQuote).execute(captorDisposableCompletableObserver.capture(), eq(quoteModelDomain))
        assert(viewModel.getLiveData().value?.status != ResourceState.LOADING)
        assert(viewModel.getLiveData().value?.data == null)
        assert(viewModel.getLiveData().value?.message == null)
    }

    @Test
    fun saveQuoteReturnsSuccess() {
        // 1.Prepare
        val quoteModelDomain = QuoteModelDomain("quote domain model")
        whenever(mapper.mapFromPresentation(any())).thenReturn(quoteModelDomain)
        // 2. Execute
        viewModel.saveQuote("quote to save")
        // 3. Verify
        verify(saveQuote).execute(captorDisposableCompletableObserver.capture(), eq(quoteModelDomain))
        captorDisposableCompletableObserver.firstValue.onComplete()
        assert(viewModel.getLiveData().value?.data == null)
        assert(viewModel.getLiveData().value?.message == null)
    }

    @Test
    fun saveQuoteReturnsError() {
        // 1.Prepare
        val errorText = "test error"
        val quoteModelDomain = QuoteModelDomain("quote domain model")
        whenever(mapper.mapFromPresentation(any())).thenReturn(quoteModelDomain)
        // 2. Execute
        viewModel.saveQuote("quote to save")
        // 3. Verify
        verify(saveQuote).execute(captorDisposableCompletableObserver.capture(), eq(quoteModelDomain))
        captorDisposableCompletableObserver.firstValue.onError(RuntimeException(errorText))
        assert(viewModel.getLiveData().value?.status == ResourceState.ERROR)
        assert(viewModel.getLiveData().value?.data == null)
        assert(viewModel.getLiveData().value?.message == errorText)
    }

    @Test
    fun clearQuoteDoesNotTriggerLoading() {
        // 2. Execute
        viewModel.clearQuotes()
        // 3. Verify
        assert(viewModel.getLiveData().value?.status != ResourceState.LOADING)
        assert(viewModel.getLiveData().value?.data == null)
        assert(viewModel.getLiveData().value?.message == null)
    }

    @Test
    fun clearQuoteReturnsSuccess() {
        // 1.Prepare
        val quoteModelDomain = QuoteModelDomain("quote domain model")
        whenever(mapper.mapFromPresentation(any())).thenReturn(quoteModelDomain)
        // 2. Execute
        viewModel.clearQuotes()
        // 3. Verify
        verify(clearQuote).execute(captorDisposableCompletableObserver.capture(), eq(""))
        captorDisposableCompletableObserver.firstValue.onComplete()
        assert(viewModel.getLiveData().value?.status == ResourceState.SUCCESS)
        assert(viewModel.getLiveData().value?.data == null)
        assert(viewModel.getLiveData().value?.message == null)
    }

    @Test
    fun clearQuoteReturnsError() {
        // 1.Prepare
        val errorText = "test error"
        // 2. Execute
        viewModel.clearQuotes()
        // 3. Verify
        verify(clearQuote).execute(captorDisposableCompletableObserver.capture(), eq(""))
        captorDisposableCompletableObserver.firstValue.onError(RuntimeException(errorText))
        assert(viewModel.getLiveData().value?.status == ResourceState.ERROR)
        assert(viewModel.getLiveData().value?.data == null)
        assert(viewModel.getLiveData().value?.message == errorText)
    }
}