package kioli.koro.remote

import io.reactivex.Flowable
import kioli.koro.remote.model.QuoteModelRemote
import retrofit2.http.GET

/**
 * Defines the abstract methods used for interacting with the User API
 */
interface QuoteService {

    // Object set to be able to assign constants in an interface
    object ApiSettings {
        // Constants expressed with 'const' that forces to give a value at compile time instead of runtime
        // if there was only 'val' they could have been assigned a function
        const val randomQuote = "?method=getQuote&format=json&lang=en"
    }

    @GET(ApiSettings.randomQuote)
    fun loadQuote(): Flowable<QuoteModelRemote>

}