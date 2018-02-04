package kioli.koro.presentation.mapper

/**
 * Interface for model mappers.
 * It provides helper methods that facilitate retrieving of models from outer data source layers
 *
 * @param <P> the PRESENTATION model input type
 * @param <D> the DOMAIN model return type
 */
interface PresentationMapper<P, D> {

    fun mapFromPresentation(type: P): D

    fun mapToPresentation(type: D): P

}