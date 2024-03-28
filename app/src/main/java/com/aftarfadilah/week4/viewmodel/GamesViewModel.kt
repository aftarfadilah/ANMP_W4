package com.aftarfadilah.week4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.aftarfadilah.week4.model.Games
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GamesViewModel(application: Application): AndroidViewModel(application) {
    val gamesLD = MutableLiveData<ArrayList<Games>>()
    val gamesLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    init {
        queue = Volley.newRequestQueue(getApplication())
    }

    fun refresh() {
        loadingLD.value = true
        gamesLoadErrorLD.value = false

        queue?.let { queue ->
            val url = "http://10.0.2.2/api/games/list.json"

            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    val sType = object : TypeToken<List<Games>>() { }.type
                    val result = Gson().fromJson<List<Games>>(response, sType)
                    gamesLD.value = result as ArrayList<Games>?
                    loadingLD.value = false
                    Log.d("showvoley", "Games Response: $response") // Log the response from the server
                    Log.d("showvoley", "Parsed Result: $result") // Log the parsed result
                },
                { error ->
                    Log.d("showvoley", "Game Error: $error") // Log any error that occurred
                    gamesLoadErrorLD.value = true
                    loadingLD.value = false
                })

            stringRequest.tag = TAG
            queue?.add(stringRequest)
        }
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}