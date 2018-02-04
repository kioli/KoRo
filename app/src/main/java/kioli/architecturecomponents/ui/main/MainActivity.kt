package kioli.architecturecomponents.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kioli.architecturecomponents.App
import kioli.architecturecomponents.R
import kioli.architecturecomponents.db.entry.User
import kioli.architecturecomponents.spPassword
import kioli.architecturecomponents.spUsername
import kioli.architecturecomponents.ui.intro.IntroView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val sharedPref = App.pref

    internal companion object {
        const val extraUser = "extra user"

        fun getLaunchIntent(context: Context, user: User): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(extraUser, user)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_result.text = String.format(resources.getString(R.string.main_text), intent.getParcelableExtra<User>(extraUser).username)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_logout -> {
            sharedPref.edit().apply {
                remove(spUsername)
                remove(spPassword)
            }.apply()
            startActivity(Intent(baseContext, IntroView::class.java))
            finish()
            true
        }
        else ->
            super.onOptionsItemSelected(item)
    }
}