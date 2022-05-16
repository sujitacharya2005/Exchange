package com.android.exchange.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.exchange.CoinExchangeRateApplication
import com.android.exchange.R
import com.android.exchange.adapter.CoinExchangeRateAdapter
import com.android.exchange.databinding.CoinExchangeRateFragmentBinding
import com.android.exchange.viewmodel.CoinExchangeRateViewModel
import com.android.exchange.viewmodel.CoinExchangeRateViewModelFactory

private const val FETCH_IN_DELAY = 5000L

class CoinExchangeRateFragment : Fragment() {

    lateinit var viewModel: CoinExchangeRateViewModel
    private lateinit var coinExchangeRateAdapter: CoinExchangeRateAdapter

    lateinit var handler: Handler

    private lateinit var binding: CoinExchangeRateFragmentBinding

    /**
     * This is get crypto data every 5 seconds
     */
    private val updateTask = object : Runnable {
        override fun run() {
            viewModel.getRates()
            handler.postDelayed(this, FETCH_IN_DELAY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        setHasOptionsMenu(true)
        binding = CoinExchangeRateFragmentBinding.inflate(inflater, container, false)
        initRecyclerView()
        observeCoinsExchangeRate()
        handler = Handler(Looper.getMainLooper())
        return binding.root
    }

    private fun observeCoinsExchangeRate() {
        val repository =
            (activity?.application as CoinExchangeRateApplication).coinExchangeRateRepository
        viewModel = ViewModelProvider(this, CoinExchangeRateViewModelFactory(repository)).get(
            CoinExchangeRateViewModel::class.java)

        viewModel.coinsExchangeRateLiveData.observe(viewLifecycleOwner, {
            coinExchangeRateAdapter.submitList(it)
        })
    }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        coinExchangeRateAdapter = CoinExchangeRateAdapter()
        recyclerView.adapter = coinExchangeRateAdapter
    }

    override fun onResume() {
        super.onResume()
        handler.post(updateTask)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(updateTask)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.more_menu, menu)
    }


    companion object {
        fun newInstance(): CoinExchangeRateFragment {
            return CoinExchangeRateFragment()
        }
    }
}