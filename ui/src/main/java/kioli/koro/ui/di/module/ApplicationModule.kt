package kioli.koro.ui.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import kioli.koro.cache.QuoteCacheImpl
import kioli.koro.cache.db.Db
import kioli.koro.cache.mapper.QuoteCacheMapper
import kioli.koro.data.executor.JobExecutor
import kioli.koro.data.mapper.QuoteDataMapper
import kioli.koro.data.repository.QuoteCache
import kioli.koro.data.repository.QuoteDataRepository
import kioli.koro.data.repository.QuoteRemote
import kioli.koro.data.store.QuoteDataStoreFactory
import kioli.koro.domain.executor.PostExecutionThread
import kioli.koro.domain.executor.ThreadExecutor
import kioli.koro.domain.repository.QuoteRepository
import kioli.koro.remote.QuoteRemoteImpl
import kioli.koro.remote.QuoteService
import kioli.koro.remote.QuoteServiceFactory
import kioli.koro.remote.mapper.QuoteRemoteMapper
import kioli.koro.ui.BuildConfig
import kioli.koro.ui.UiThread
import kioli.koro.ui.di.scope.PerApplication

/**
 * Module used to provide dependencies at the Application level
 */
@Module
class ApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun provideDatabase(application: Application): Db {
        return Room.databaseBuilder(application.applicationContext, Db::class.java, "kioli.db").build()
    }

    @Provides
    @PerApplication
    internal fun provideQuoteCache(database: Db,
                                   entityMapper: QuoteCacheMapper): QuoteCache {
        return QuoteCacheImpl(database, entityMapper)
    }

    @Provides
    @PerApplication
    internal fun provideQuoteRepository(factory: QuoteDataStoreFactory,
                                        mapper: QuoteDataMapper): QuoteRepository {
        return QuoteDataRepository(factory, mapper)
    }

    @Provides
    @PerApplication
    internal fun provideQuoteRemote(service: QuoteService,
                                    factory: QuoteRemoteMapper): QuoteRemote {
        return QuoteRemoteImpl(service, factory)
    }

    @Provides
    @PerApplication
    internal fun provideQuoteService(): QuoteService {
        return QuoteServiceFactory.makeService(BuildConfig.DEBUG)
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }
}