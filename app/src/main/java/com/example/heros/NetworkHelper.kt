package com.example.heros

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log

class NetworkHelper(private val context: Context) {

    private val connectivityManager = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)

    fun observeNetwork(onAvailable: () -> Unit, onLost: () -> Unit) {
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
               Log.v("Network state", "@@ Network available")
                onAvailable.invoke()
            }
            override fun onLost(network: Network) {
                Log.v("Network state", "@@ Network Lost")
                onLost.invoke()
            }
        })
    }
}