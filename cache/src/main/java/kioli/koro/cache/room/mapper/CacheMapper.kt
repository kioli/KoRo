package kioli.koro.cache.room.mapper

/**
 * Interface for model mappers.
 * It provides helper methods that facilitate retrieving of models from outer data source layers
 *
 * @param <C> the CACHE model input type
 * @param <D> the DATA model return type
 */
interface CacheMapper<C, D> {

    fun mapFromCached(type: C): D

    fun mapToCached(type: D): C

}