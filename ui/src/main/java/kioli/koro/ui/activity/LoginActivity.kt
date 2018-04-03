package kioli.koro.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import dagger.android.AndroidInjection
import kioli.koro.presentation.data.Resource
import kioli.koro.presentation.data.ResourceState
import kioli.koro.presentation.model.UserModelPresentation
import kioli.koro.presentation.viewmodel.LoginViewModel
import kioli.koro.presentation.viewmodel.ViewModelFactory
import kioli.koro.ui.R
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var auth: FirebaseAuth
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        AndroidInjection.inject(this)
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        btn_login.setOnClickListener { loginViewModel.loginUser(username_text.text.toString(), password_text.text.toString()) }
        btn_register.setOnClickListener { loginViewModel.registerUser(username_text.text.toString(), password_text.text.toString()) }
        loginViewModel.getLiveData().observe(this,
                Observer<Resource<UserModelPresentation>> {
                    if (it != null) handleDataState(it.status, it.data, it.message)
                })
    }

    override fun onStart() {
        super.onStart()
        auth.currentUser?.let {
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
        }
    }

    private fun handleDataState(state: ResourceState, data: UserModelPresentation?, message: String?) {
        when (state) {
            ResourceState.LOADING -> setupScreenForLoadingState()
            ResourceState.SUCCESS -> setupScreenForSuccess(data)
            ResourceState.ERROR -> setupScreenForError(message)
        }
    }

    private fun setupScreenForLoadingState() {
        loading.visibility = View.VISIBLE
    }

    private fun setupScreenForSuccess(data: UserModelPresentation?) {
        data?.let {
            loading.visibility = View.GONE
            startActivity(Intent(this, IntroActivity::class.java))
        }
    }

    private fun setupScreenForError(message: String?) {
        loading.visibility = View.GONE
        Toast.makeText(baseContext, "ERROR: $message", Toast.LENGTH_SHORT).show()
    }
}
