package kioli.architecturecomponents.ui.intro

import kioli.architecturecomponents.Either
import kioli.architecturecomponents.Left
import kioli.architecturecomponents.Right
import kioli.architecturecomponents.db.entry.User
import kioli.architecturecomponents.db.entry.UserError
import kioli.architecturecomponents.mvp.IModel

internal class IntroModel(override val status: Either<UserError, User> = Left(UserError("Input invalid")))
    : IModel<Either<UserError, User>> {

    fun invalidInput() = IntroModel(Left(UserError("Input invalid")))

    fun userAlreadyRegistered() = IntroModel(Left(UserError("Username already registered")))

    fun userNotFound() = IntroModel(Left(UserError("User not found")))

    fun correctCredentials(user: User) = IntroModel(Right(user))
}