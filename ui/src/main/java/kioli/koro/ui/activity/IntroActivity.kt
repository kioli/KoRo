package kioli.koro.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import dagger.android.AndroidInjection
import kioli.koro.presentation.data.Resource
import kioli.koro.presentation.data.ResourceState
import kioli.koro.presentation.model.QuoteModelPresentation
import kioli.koro.presentation.viewmodel.IntroViewModel
import kioli.koro.presentation.viewmodel.ViewModelFactory
import kioli.koro.ui.R
import kotlinx.android.synthetic.main.activity_intro.*
import javax.inject.Inject

class IntroActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var introViewModel: IntroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        AndroidInjection.inject(this)
        introViewModel = ViewModelProviders.of(this, viewModelFactory).get(IntroViewModel::class.java)

        btn_load_quote.setOnClickListener { introViewModel.loadQuote() }
        btn_save_quote.setOnClickListener { introViewModel.saveQuote(quote_result.text.toString()) }
        btn_clear_quote.setOnClickListener { introViewModel.clearQuotes() }
    }

    override fun onStart() {
        super.onStart()
        introViewModel.getLiveData().observe(this,
                Observer<Resource<QuoteModelPresentation>> {
                    if (it != null) handleDataState(it.status, it.data, it.message)
                })
        handleDataState(ResourceState.SUCCESS, null, null)
    }

    private fun handleDataState(state: ResourceState, data: QuoteModelPresentation?, message: String?) {
        when (state) {
            ResourceState.LOADING -> setupScreenForLoadingState()
            ResourceState.SUCCESS -> setupScreenForSuccess(data)
            ResourceState.ERROR -> setupScreenForError(message)
        }
    }

    private fun setupScreenForLoadingState() {
        loading.visibility = View.VISIBLE
    }

    private fun setupScreenForSuccess(data: QuoteModelPresentation?) {
        loading.visibility = View.GONE
        quote_result.text = data?.text ?: ""
        btn_save_quote.isEnabled = data?.text?.isNotBlank() ?: false
    }

    private fun setupScreenForError(message: String?) {
        loading.visibility = View.GONE
        Toast.makeText(baseContext, "ERROR: $message", Toast.LENGTH_SHORT).show()
    }
}
