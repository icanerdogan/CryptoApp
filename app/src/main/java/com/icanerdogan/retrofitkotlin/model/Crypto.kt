package com.icanerdogan.retrofitkotlin.model

import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat

data class Crypto(
    @SerializedName("name")
    val cryptoName: String,
    @SerializedName("price_usd")
    val cryptopPrice: Double?
) {
    fun formatPrice(): String {
        if (cryptopPrice == null) {
            return "0"
        } else {
            return DecimalFormat("#.##").format(cryptopPrice)
        }
    }
}
