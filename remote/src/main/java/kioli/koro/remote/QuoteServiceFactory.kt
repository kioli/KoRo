package kioli.koro.remote

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Provide "make" methods to create instances of [QuoteService]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */
object QuoteServiceFactory {

    private const val baseUrl = "http://api.forismatic.com/api/1.0/"

    fun makeService(isDebug: Boolean): QuoteService {
        val okHttpClient = makeOkHttpClient(makeLoggingInterceptor(isDebug))
        return makeService(okHttpClient, makeGson())
    }

    private fun makeService(okHttpClient: OkHttpClient, gson: Gson): QuoteService {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(QuoteService::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
    }

    private fun makeGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.NONE
        if (isDebug) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        }
        return logging
    }
}