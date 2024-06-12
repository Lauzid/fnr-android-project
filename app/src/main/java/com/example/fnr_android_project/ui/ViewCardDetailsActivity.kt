package com.example.fnr_android_project.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fnr_android_project.R
import com.example.fnr_android_project.databinding.ActivityViewCardDetailsBinding
import com.example.fnr_android_project.model.Card
import com.example.fnr_android_project.network.Network
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewCardDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewCardDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityViewCardDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recebe o cardId passado pela Intent
        val cardId = intent.getStringExtra("cardId")

        // Chama a API para obter detalhes da carta
        if (cardId != null) {
            fetchCardDetails(cardId)
        }
    }

    private fun fetchCardDetails(cardId: String) {
        val call = Network.api.getCardDetails(cardId)
        call?.enqueue(object : Callback<Card?> {
            override fun onResponse(call: Call<Card?>, response: Response<Card?>) {
                if (response.isSuccessful) {
                    val card = response.body()
                    if (card != null) {
                        binding.cardName.text = card.name
                        binding.cardType.text = card.type_line
                        binding.cardManaCost.text = card.mana_cost
                        binding.cardOracleText.text = card.oracle_text
                        Picasso.get()
                            .load(card.image_uris.normal)
                            .into(binding.cardImage)
                    }
                }
            }

            override fun onFailure(call: Call<Card?>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
