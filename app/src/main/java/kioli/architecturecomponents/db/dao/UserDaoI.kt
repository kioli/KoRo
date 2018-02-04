package kioli.architecturecomponents.db.dao

import kioli.architecturecomponents.db.entry.User
import kotlinx.coroutines.experimental.Deferred

internal interface UserDaoI {
    fun getUser(username: String, password: String): Deferred<User?>
    fun deleteUser(user: User): Deferred<Unit>
    fun deleteAllUsers(): Deferred<Unit>
    fun insertUser(user: User): Deferred<Unit>
}