package kioli.koro.data.mapper

/**
 * Interface for model mappers.
 * It provides helper methods that facilitate retrieving of models from outer data source layers
 *
 * @param <DA> the DATA model input type
 * @param <DO> the DOMAIN model return type
 */
interface DataMapper<DA, DO> {

    fun mapFromData(type: DA): DO

    fun mapToData(type: DO): DA

}