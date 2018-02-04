package kioli.architecturecomponents.db.dao

import android.arch.persistence.room.*
import kioli.architecturecomponents.db.entry.User

@Dao
internal interface UserRoomDao {

    @Query("SELECT * FROM users WHERE username LIKE :username AND password LIKE :password LIMIT 1")
    fun getUser(username: String, password: String): User?

    @Query("DELETE FROM users")
    fun deleteAllUsers()

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)
}