package kioli.koro.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import dagger.android.AndroidInjection
import kioli.koro.presentation.data.Resource
import kioli.koro.presentation.data.ResourceState
import kioli.koro.presentation.model.QuoteModelPresentation
import kioli.koro.presentation.viewmodel.QuoteViewModel
import kioli.koro.presentation.viewmodel.ViewModelFactory
import kioli.koro.ui.R
import kioli.koro.ui.databinding.ActivityQuoteBinding
import kotlinx.android.synthetic.main.activity_quote.*
import javax.inject.Inject

class QuoteActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: ActivityQuoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_quote)
        binding.vm = ViewModelProviders.of(this, viewModelFactory).get(QuoteViewModel::class.java)

        btn_load_quote.setOnClickListener { binding.vm?.loadQuote() }
        btn_save_quote.setOnClickListener { binding.vm?.saveQuote(quote_result.text.toString()) }
        btn_clear_quote.setOnClickListener { binding.vm?.clearQuotes() }
    }

    override fun onStart() {
        super.onStart()
        binding.vm?.getLiveData()?.observe(this,
                Observer<Resource<QuoteModelPresentation>> {
                    if (it != null) handleState(it.status, it.data, it.message)
                })
        handleState(ResourceState.SUCCESS, null, null)
    }

    private fun handleState(state: ResourceState, data: QuoteModelPresentation?, message: String?) {
        when (state) {
            ResourceState.LOADING -> setupScreenForLoading()
            ResourceState.SUCCESS -> setupScreenForSuccess(data)
            ResourceState.ERROR -> setupScreenForError(message)
        }
    }

    private fun setupScreenForLoading() {
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
