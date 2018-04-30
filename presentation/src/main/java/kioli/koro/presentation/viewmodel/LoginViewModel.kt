package kioli.koro.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.text.TextUtils
import android.util.Patterns
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kioli.koro.presentation.data.Resource
import kioli.koro.presentation.data.ResourceState
import kioli.koro.presentation.model.UserModelPresentation
import java.util.regex.Pattern
import javax.inject.Inject

open class LoginViewModel @Inject internal constructor(private val auth: FirebaseAuth)
    : ViewModel() {

    private val wrongEmail = "Insert valid email"
    private val wrongPassword = "Password must be at least 8 characters long and contain at least one number, one upper case and one lower case character"
    private val missingInput = "Provide both email and password"
    private val failLogin = "Login failed"
    private val failRegistration = "Registration failed, user already present"

    private val passwordPattern = "^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[#\$@!%&*?^.]).{8,63})$"
    private val userLiveData: MutableLiveData<Resource<UserModelPresentation>> = MutableLiveData()

    fun getLiveData(): LiveData<Resource<UserModelPresentation>> = userLiveData

    fun automaticLogin() {
        auth.currentUser?.let {
            val user = UserModelPresentation(it.email!!, "")
            userLiveData.postValue(Resource(ResourceState.SUCCESS, user, null))
        }
    }

    fun loginUser(email: String, password: String) {
        val cleanEmail = email.trim()
        val cleanPassword = password.trim()
        userLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        if (isInputValid(cleanEmail, cleanPassword)) {
            auth.signInWithEmailAndPassword(cleanEmail, cleanPassword).addOnCompleteListener(LoginSubscriber())
        }
    }

    fun registerUser(email: String, password: String) {
        val cleanEmail = email.trim()
        val cleanPassword = password.trim()
        userLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        if (isInputValid(cleanEmail, cleanPassword)) {
            auth.createUserWithEmailAndPassword(cleanEmail, cleanPassword).addOnCompleteListener(RegistrationSubscriber())
        }
    }

    private fun isInputValid(email: String?, password: String?): Boolean {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            userLiveData.postValue(Resource(ResourceState.ERROR, null, missingInput))
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userLiveData.postValue(Resource(ResourceState.ERROR, null, wrongEmail))
            return false
        }
        if (!Pattern.matches(passwordPattern, password)) {
            userLiveData.postValue(Resource(ResourceState.ERROR, null, wrongPassword))
            return false
        }
        return true
    }

    inner class RegistrationSubscriber : OnCompleteListener<AuthResult> {
        override fun onComplete(task: Task<AuthResult>) {
            if (task.isSuccessful) {
                val user = UserModelPresentation(auth.currentUser?.email!!, "")
                userLiveData.postValue(Resource(ResourceState.SUCCESS, user, null))
                return
            }
            userLiveData.postValue(Resource(ResourceState.ERROR, null, failRegistration))
        }
    }

    inner class LoginSubscriber : OnCompleteListener<AuthResult> {
        override fun onComplete(task: Task<AuthResult>) {
            if (task.isSuccessful) {
                val user = UserModelPresentation(auth.currentUser?.email!!, "")
                userLiveData.postValue(Resource(ResourceState.SUCCESS, user, null))
                return
            }
            userLiveData.postValue(Resource(ResourceState.ERROR, null, failLogin))
        }
    }
}