package com.toxin.scancoin.adapters

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.toxin.scancoin.R
import com.toxin.scancoin.components.MyViewHolder
import com.toxin.scancoin.models.CryptoInfo
import kotlinx.android.synthetic.main.item_coin.view.*

class CoinAdapter : RecyclerView.Adapter<MyViewHolder>() {
    var data: List<CryptoInfo>? = null
        set (value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_coin, parent, false)
        )
    }

    override fun getItemCount(): Int = data?.size?:0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        data?.let {
            val coin = it[position]
            holder.view.tvSymbol.text = coin.symbol
            holder.view.tvName.text = coin.name
            holder.view.tvPrice.text = "${coin.price_usd}$"

            val str: String
            if (coin.percent_change_1h > 0) {
                str = "+${coin.percent_change_1h}%"
                holder.view.tvChange.setTextColor(Color.GREEN)
            } else {
                str = "-${coin.percent_change_1h}%"
                holder.view.tvChange.setTextColor(Color.RED)
            }

            holder.view.tvChange.text = str
        }
    }
}