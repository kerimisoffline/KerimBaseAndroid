package com.kuarkdijital.kerimbaseproject.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.activity.viewModels
import kotlinx.coroutines.flow.collect
import androidx.lifecycle.lifecycleScope
import com.kuarkdijital.kerimbaseproject.R
import com.kuarkdijital.kerimbaseproject.bindings.viewBindingWithBinder
import com.kuarkdijital.kerimbaseproject.databinding.MainFragmentBinding
import com.kuarkdijital.kerimbaseproject.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel : MainViewModel

    private val binding by viewBindingWithBinder(MainFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.message.setOnClickListener {
            viewModel.convert("1000","TRY","EN")
        }

        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { event->
                when(event){
                    is MainViewModel.CurrencyEvent.Success -> {
                        // binding.progressBar.isVisible = false
                        //binding.tvResult.setTextColor(Color.BLACK)
                        //binding.tvResult.text = event.resultText
                    }

                    is MainViewModel.CurrencyEvent.Failure -> {
                        // binding.progressBar.isVisible = true
                        //binding.tvResult.setTextColor(Color.RED)
                        //binding.tvResult.text = event.errorText
                    }

                    is MainViewModel.CurrencyEvent.Loading -> {
                        // binding.progressBar.isVisible = true
                        //binding.tvResult.setTextColor(Color.BLACK)
                    }

                    else -> Unit
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

}