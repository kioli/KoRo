package kioli.koro.ui

import android.arch.persistence.room.Room
import kioli.koro.cache.room.QuoteCacheImpl
import kioli.koro.cache.room.db.Db
import kioli.koro.cache.room.mapper.QuoteCacheMapper
import kioli.koro.data.executor.JobExecutor
import kioli.koro.data.mapper.QuoteDataMapper
import kioli.koro.data.repository.QuoteCache
import kioli.koro.data.repository.QuoteDataRepository
import kioli.koro.data.repository.QuoteRemote
import kioli.koro.data.store.QuoteStoreCache
import kioli.koro.data.store.QuoteStoreFactory
import kioli.koro.data.store.QuoteStoreRemote
import kioli.koro.domain.executor.PostExecutionThread
import kioli.koro.domain.executor.ThreadExecutor
import kioli.koro.domain.interactor.ClearQuoteUseCase
import kioli.koro.domain.interactor.LoadQuoteUseCase
import kioli.koro.domain.interactor.SaveQuoteUseCase
import kioli.koro.domain.repository.QuoteRepository
import kioli.koro.presentation.mapper.QuotePresentationMapper
import kioli.koro.presentation.viewmodel.QuoteViewModel
import kioli.koro.presentation.viewmodel.ViewModelFactory
import kioli.koro.remote.QuoteRemoteImpl
import kioli.koro.remote.QuoteService
import kioli.koro.remote.QuoteServiceFactory
import kioli.koro.remote.mapper.QuoteRemoteMapper
import kioli.koro.ui.activity.QuoteActivity
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import org.kodein.di.newInstance

class KodeinCreator : App.Creator {

    private val kodein = Kodein {
        bind<App>() with singleton { App() }
        bind<ThreadExecutor>() with singleton { JobExecutor() }
        bind<PostExecutionThread>() with singleton { UiThread() }

        bind<QuoteCacheMapper>() with provider { QuoteCacheMapper() }
        bind<QuoteDataMapper>() with provider { QuoteDataMapper() }
        bind<QuotePresentationMapper>() with provider { QuotePresentationMapper() }
        bind<QuoteRemoteMapper>() with provider { QuoteRemoteMapper() }

        bind<QuoteService>() with singleton { QuoteServiceFactory.makeService(BuildConfig.DEBUG) }
        bind<Db>() with singleton {
            val context = instance<App>().applicationContext
            Room.databaseBuilder(context, Db::class.java, "kioli.db").build()
        }
        bind<QuoteCache>() with singleton {
            val db = instance<Db>()
            val mapper = instance<QuoteCacheMapper>()
            QuoteCacheImpl(db, mapper)
        }
        bind<QuoteRemote>() with singleton {
            val service = instance<QuoteService>()
            val mapper = instance<QuoteRemoteMapper>()
            QuoteRemoteImpl(service, mapper)
        }
        bind<QuoteStoreFactory>() with singleton {
            val storeCache = instance<QuoteStoreCache>()
            val storeRemote = instance<QuoteStoreRemote>()
            QuoteStoreFactory(storeCache, storeRemote)
        }
        bind<QuoteStoreCache>() with singleton {
            val cache = instance<QuoteCache>()
            QuoteStoreCache(cache)
        }
        bind<QuoteStoreRemote>() with singleton {
            val remote = instance<QuoteRemote>()
            QuoteStoreRemote(remote)
        }
        bind<QuoteRepository>() with singleton {
            val factory = instance<QuoteStoreFactory>()
            val mapper = instance<QuoteDataMapper>()
            QuoteDataRepository(factory, mapper)
        }
        bind<LoadQuoteUseCase>() with singleton {
            val repository = instance<QuoteRepository>()
            val threadExecutor = instance<ThreadExecutor>()
            val postExecutionThread = instance<PostExecutionThread>()
            LoadQuoteUseCase(repository, threadExecutor, postExecutionThread)
        }
        bind<SaveQuoteUseCase>() with singleton {
            val repository = instance<QuoteRepository>()
            val threadExecutor = instance<ThreadExecutor>()
            val postExecutionThread = instance<PostExecutionThread>()
            SaveQuoteUseCase(repository, threadExecutor, postExecutionThread)
        }
        bind<ClearQuoteUseCase>() with singleton {
            val repository = instance<QuoteRepository>()
            val threadExecutor = instance<ThreadExecutor>()
            val postExecutionThread = instance<PostExecutionThread>()
            ClearQuoteUseCase(repository, threadExecutor, postExecutionThread)
        }
        bind<QuoteViewModel>() with singleton {
            val loadQuoteUseCase = instance<LoadQuoteUseCase>()
            val saveQuoteUseCase = instance<SaveQuoteUseCase>()
            val clearQuoteUseCase = instance<ClearQuoteUseCase>()
            val mapper = instance<QuotePresentationMapper>()
            QuoteViewModel(loadQuoteUseCase, saveQuoteUseCase, clearQuoteUseCase, mapper)
        }
        bind<ViewModelFactory>() with singleton {
            val quoteViewModel = instance<QuoteViewModel>()
            ViewModelFactory(quoteViewModel)
        }
    }.direct

    override fun quoteActivity() = kodein.newInstance { QuoteActivity.Dependencies(instance()) }
}