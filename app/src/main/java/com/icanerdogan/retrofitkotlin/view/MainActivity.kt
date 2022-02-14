package com.icanerdogan.retrofitkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.icanerdogan.retrofitkotlin.R
import com.icanerdogan.retrofitkotlin.adapter.RecyclerViewAdapter
import com.icanerdogan.retrofitkotlin.databinding.ActivityMainBinding
import com.icanerdogan.retrofitkotlin.model.Crypto
import com.icanerdogan.retrofitkotlin.service.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.Listener {
    private val BASE_URL = "https://rest.coinapi.io/v1/"
    private var cryptoModels: ArrayList<Crypto>? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Recycler View
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        loadData()
    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getAllData()

        call.enqueue(object : Callback<List<Crypto>> {
            override fun onResponse(call: Call<List<Crypto>>, response: Response<List<Crypto>>) {
                if (response.isSuccessful) {
                    // Bu Boş gelmediyse şunu yap
                    response.body()?.let {
                        cryptoModels = ArrayList(it)

                        cryptoModels?.let {
                            recyclerViewAdapter = RecyclerViewAdapter(it, this@MainActivity)
                            binding.recyclerView.adapter = recyclerViewAdapter
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Crypto>>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }

    override fun onItemClick(crypto: Crypto) {
        Toast.makeText(this, "Clicked: ${crypto.cryptoName}", Toast.LENGTH_LONG).show()
    }
}