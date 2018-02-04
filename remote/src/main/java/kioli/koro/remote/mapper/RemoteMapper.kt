package kioli.koro.remote.mapper

/**
 * Interface for model mappers.
 * It provides helper methods that facilitate retrieving of models from outer data source layers
 *
 * @param <R> the REMOTE model input type
 * @param <D> the DATA model return type
 */
interface RemoteMapper<in R, out D> {

    fun mapFromRemote(type: R): D

}