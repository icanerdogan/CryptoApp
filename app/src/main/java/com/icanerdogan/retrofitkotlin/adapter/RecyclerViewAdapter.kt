package com.icanerdogan.retrofitkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.icanerdogan.retrofitkotlin.R
import com.icanerdogan.retrofitkotlin.databinding.RowLayoutBinding
import com.icanerdogan.retrofitkotlin.model.Crypto

class RecyclerViewAdapter(
    private val cryptoList: ArrayList<Crypto>,
    private val listener: Listener
) :
    RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface Listener {
        fun onItemClick(crypto: Crypto)
    }

    class RowHolder(val rowBinding: RowLayoutBinding) : RecyclerView.ViewHolder(rowBinding.root) {
        fun bind(crypto: Crypto, listener: Listener) {
            rowBinding.cryptodata = crypto

            rowBinding.linearLayout.setOnClickListener {
                listener.onItemClick(crypto)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {

        return RowHolder(
            RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cryptoList[position], listener)

    }

    override fun getItemCount(): Int {
        return cryptoList.count()
    }
}