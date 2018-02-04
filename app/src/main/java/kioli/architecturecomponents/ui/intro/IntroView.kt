package kioli.architecturecomponents.ui.intro

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kioli.architecturecomponents.R
import kioli.architecturecomponents.fold
import kioli.architecturecomponents.mvp.IView
import kioli.architecturecomponents.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_intro.*

internal class IntroView : AppCompatActivity(), IView<IntroModel> {

    private val presenter = IntroPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        btn_login.setOnClickListener {
            presenter.login(input_username.text.toString(), input_password.text.toString())
        }
        btn_register.setOnClickListener {
            presenter.register(input_username.text.toString(), input_password.text.toString())
        }
        presenter.autoLogin()
    }

    override fun render(model: IntroModel) {
        model.status.fold(
                { Toast.makeText(baseContext, it.message, Toast.LENGTH_SHORT).show() },
                {
                    startActivity(MainActivity.getLaunchIntent(baseContext, it))
                    finish()
                }
        )
    }
}
