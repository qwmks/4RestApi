package com.example.a4restapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val BASE_URL = "https://world.openfoodfacts.org/api/v0/"
        setContentView(R.layout.activity_main)
        fun getReport(){
            val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val service = retrofit.create(RestAPI::class.java)
            val call : Call<Food?>? = service.getFood("089686140101")
            call?.enqueue(object : Callback<Food?> {
                override fun onResponse(call: Call<Food?>, response: Response<Food?>) {
                    Log.d("Ready", response.body().toString())
                    try {
                        showRes(response.body()!!)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                override fun onFailure(call: Call<Food?>, t: Throwable) {
                    Log.d("fail", t.message.toString());
                }
            })
        }
        val but = findViewById<Button>(R.id.button)
        but.setOnClickListener {
            getReport()
        }

    }

    private fun showRes(body: Food) {
        val name = findViewById<TextView>(R.id.name)
        val quantity = findViewById<TextView>(R.id.quantity)
        val popularity = findViewById<TextView>(R.id.popularity)
        name.text = body.product.product_name
        quantity.text= body.product.quantity
        popularity.text= body.product.popularity_key.toString()
    }
}