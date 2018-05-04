package com.toxin.scancoin.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class CryptoInfo {
    lateinit var name: String
    lateinit var symbol: String
    var price_usd: Double = 0.0
    var percent_change_1h: Double = 0.0
}