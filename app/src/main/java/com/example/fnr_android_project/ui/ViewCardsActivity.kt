package com.example.fnr_android_project.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.fnr_android_project.databinding.ActivityViewCardsBinding
import com.example.fnr_android_project.model.BaseSetResponse
import com.example.fnr_android_project.model.Set
import com.example.fnr_android_project.network.Network
import retrofit2.Call
import retrofit2.Response

class ViewCardsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewCardsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityViewCardsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val set = intent.getParcelableExtra<Set>(SET)
        Network.api.getCards("set:${set?.code}").enqueue(object : retrofit2.Callback<BaseSetResponse> {
            override fun onResponse(
                call: Call<BaseSetResponse>,
                response: Response<BaseSetResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    //binding.set.setText(responseBody?.data.toString())
                    binding.cardRecyclerView.setText(responseBody?.data.toString())
                } else {

                }
            }

            override fun onFailure(call: Call<BaseSetResponse>, t: Throwable) {
                t.printStackTrace()
            }


        })
    }
}