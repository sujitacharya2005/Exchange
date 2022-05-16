package com.android.exchange.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.exchange.databinding.CoinExchangeHeaderBinding
import com.android.exchange.databinding.CoinExchangeItemBinding
import com.android.exchange.model.CryptoData

private const val TYPE_HEADER = 0
private const val TYPE_ITEM = 1

/**
 * This Adapter class is responsible to display data in RecyclerView
 * This adapter is of type ListAdapter
 */
class CoinExchangeRateAdapter :
    ListAdapter<CryptoData, RecyclerView.ViewHolder>(DiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        if (viewType == TYPE_HEADER) {//This is header type
            return ViewHolderHeader(CoinExchangeHeaderBinding.inflate(layoutInflater,
                parent,
                false).root)
        }

        return CoinViewHolder(CoinExchangeItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CoinViewHolder)
            holder.bind(getItem(position))
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER else TYPE_ITEM
    }

    class CoinViewHolder(private val binding: CoinExchangeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(uiData: CryptoData) {
            binding.cryptoData = uiData
        }
    }

    class ViewHolderHeader(itemView: View) : RecyclerView.ViewHolder(itemView)
}