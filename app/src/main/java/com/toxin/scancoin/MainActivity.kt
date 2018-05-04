package com.toxin.scancoin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.toxin.scancoin.adapters.CoinAdapter
import com.toxin.scancoin.api.ServiceGenerator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_coin.view.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CoinAdapter
    private lateinit var swipe: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CoinAdapter()
        recyclerView.adapter = adapter
        loadCoins()

        swipe = SwipeUpdate
        swipe.setOnRefreshListener {
            loadCoins()
            Toast.makeText(
                    this@MainActivity,
                    R.string.app_update,
                    Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun loadCoins() {
        launch(UI) {
            try {
                adapter.data = ServiceGenerator.serverAPI.loadData(100).await()
            } catch (ex:Exception) {
                Toast.makeText(
                        this@MainActivity,
                        R.string.app_error,
                        Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
