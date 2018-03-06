package kioli.koro.presentation.mapper

import kioli.koro.domain.model.UserModelDomain
import kioli.koro.presentation.model.UserModelPresentation
import javax.inject.Inject

/**
 * Map [UserModelPresentation] to and from a [UserModelDomain] when data moves between this and the DOMAIN layer
 */
open class UserPresentationMapper @Inject constructor() : PresentationMapper<UserModelPresentation, UserModelDomain> {

    /**
     * Map a [UserModelPresentation] instance to a [UserModelDomain] instance
     * @param type user model coming from the PRESENTATION layer
     * @return user model proper of the DOMAIN layer
     */
    override fun mapFromPresentation(type: UserModelPresentation): UserModelDomain {
        return UserModelDomain(type.username, type.password)
    }

    /**
     * Map a [UserModelDomain] instance to a [UserModelPresentation] instance
     * @param type user model coming from the DOMAIN layer
     * @return user model proper of the PRESENTATION layer
     */
    override fun mapToPresentation(type: UserModelDomain): UserModelPresentation {
        return UserModelPresentation(type.username, type.password)
    }

}