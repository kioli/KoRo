package kioli.architecturecomponents.db.dao

import kioli.architecturecomponents.db.Db
import kioli.architecturecomponents.db.entry.User
import kotlinx.coroutines.experimental.async

internal class UserDao(val db: Db) : UserDaoI {

    override fun getUser(username: String, password: String) = async { db.userDao().getUser(username, password) }

    override fun deleteAllUsers() = async { db.userDao().deleteAllUsers() }

    override fun insertUser(user: User) = async { db.userDao().insertUser(user) }

    override fun deleteUser(user: User) = async { db.userDao().deleteUser(user) }
}