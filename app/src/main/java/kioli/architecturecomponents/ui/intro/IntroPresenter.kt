package kioli.architecturecomponents.ui.intro

import android.database.sqlite.SQLiteConstraintException
import kioli.architecturecomponents.*
import kioli.architecturecomponents.db.dao.UserDao
import kioli.architecturecomponents.db.entry.User
import kioli.architecturecomponents.db.entry.UserError
import kioli.architecturecomponents.mvp.IPresenter
import kioli.architecturecomponents.mvp.IView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

internal class IntroPresenter(override val view: IView<IntroModel>) : IPresenter<IntroModel> {

    private val dao = UserDao(App.db)
    private val sharedPref = App.pref
    private var model = IntroModel()

    fun login(username: String, password: String) {
        if (isInputValid(username, password)) {
            loginUser(username, password)
            return
        }
        model = model.invalidInput()
        view.render(model)
    }

    fun register(username: String, password: String) {
        if (!isInputValid(username, password)) {
            model = model.invalidInput()
            view.render(model)
            return
        }
        val user = User(username, password)
        registerUser(user)
    }

    fun autoLogin() {
        val username = sharedPref.getString(spUsername, "")
        val password = sharedPref.getString(spPassword, "")
        if (!username.isBlank() && !password.isBlank()) {
            login(username, password)
        }
    }

    private fun loginUser(username: String, password: String) {
        launch(UI) {
            val user = dao.getUser(username, password).await()
            user?.let {
                storeUserInSp(it.username, it.password)
                model = model.correctCredentials(it)
                view.render(model)
                return@launch
            }
            model = model.userNotFound()
            view.render(model)
        }
    }

    private fun registerUser(user: User) {
        launch(UI) {
            try {
                dao.insertUser(user).await()
            } catch (e: SQLiteConstraintException) {
                model = model.userAlreadyRegistered()
                view.render(model)
                return@launch
            }
            storeUserInSp(user.username, user.password)
            model = model.correctCredentials(user)
            view.render(model)
        }
    }

    private fun isInputValid(username: String, password: String) = !username.isBlank() && !password.isBlank()

    private fun storeUserInSp(username: String, password: String) {
        sharedPref.edit().apply {
            putString(spUsername, username)
            putString(spPassword, password)
        }.apply()
    }
}