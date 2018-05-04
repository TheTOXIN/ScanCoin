package com.toxin.scancoin.api

import com.toxin.scancoin.models.CryptoInfo
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerAPI {
    @GET("ticker")
    fun loadData(@Query("limit") limit: Int=10): Deferred<List<CryptoInfo>>
}