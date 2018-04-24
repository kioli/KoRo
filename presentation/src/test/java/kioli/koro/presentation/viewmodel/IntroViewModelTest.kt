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
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.mockito.Captor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class IntroViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val loadQuote: LoadQuoteUseCase = mock()
    private val saveQuote: SaveQuoteUseCase = mock()
    private val clearQuote: ClearQuoteUseCase = mock()
    private val mapper: QuotePresentationMapper = mock()
    private val viewModel: IntroViewModel = IntroViewModel(loadQuote, saveQuote, clearQuote, mapper)

    @Captor
    private val captorDisposableSubscriber: KArgumentCaptor<DisposableSubscriber<QuoteModelDomain>> = argumentCaptor()
    private val captorDisposableCompletableObserver: KArgumentCaptor<DisposableCompletableObserver> = argumentCaptor()

    @BeforeEach
    fun init() {
        reset(loadQuote, saveQuote, clearQuote, mapper)
    }

    @Nested
    inner class LoadQuoteCases {
        @Test
        fun `load quote executes use case`() {
            // 2. Execute
            viewModel.loadQuote()
            // 3. Verify
            verify(loadQuote).execute(any(), anyOrNull())
        }

        @Test
        fun `load quote triggers loading`() {
            // 2. Execute
            viewModel.loadQuote()
            // 3. Verify
            assert(viewModel.getLiveData().value?.status == ResourceState.LOADING)
            assert(viewModel.getLiveData().value?.data == null)
            assert(viewModel.getLiveData().value?.message == null)
        }

        @Test
        fun `load quote returns success`() {
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
        fun `load quote returns error`() {
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
    }

    @Nested
    inner class SaveQuoteCases {
        @Test
        fun `save quote executes use case`() {
            // 2. Execute
            viewModel.saveQuote("Test")
            // 3. Verify
            verify(saveQuote).execute(any(), anyOrNull())
        }

        @Test
        fun `save quote doesn't trigger loading`() {
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
        fun `save quote returns success`() {
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
        fun `save quote returns error`() {
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
    }

    @Nested
    inner class ClearQuoteCases {
        @Test
        fun `clear quote executes use case`() {
            // 2. Execute
            viewModel.clearQuotes()
            // 3. Verify
            verify(clearQuote).execute(any(), anyOrNull())
        }

        @Test
        fun `clear quote doesn't trigger loading`() {
            // 2. Execute
            viewModel.clearQuotes()
            // 3. Verify
            assert(viewModel.getLiveData().value?.status != ResourceState.LOADING)
            assert(viewModel.getLiveData().value?.data == null)
            assert(viewModel.getLiveData().value?.message == null)
        }

        @Test
        fun `clear quote returns success`() {
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
        fun `clear quote returns error`() {
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
}