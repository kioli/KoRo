package kioli.koro.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Representation for a user instance for the REMOTE layer (fetched from the API)
 */
data class QuoteModelRemote(
        @SerializedName("quoteAuthor") val author: String,
        @SerializedName("quoteText") val text: String)