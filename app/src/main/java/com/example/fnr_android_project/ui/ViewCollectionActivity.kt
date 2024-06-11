package com.example.fnr_android_project.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fnr_android_project.R
import com.example.fnr_android_project.databinding.ActivityViewCollectionBinding
import com.example.fnr_android_project.model.BaseSetResponse
import com.example.fnr_android_project.model.Set
import com.example.fnr_android_project.network.Network
import com.example.fnr_android_project.ui.collection_recycler_view.CollectionRecyclerViewAdapter
import retrofit2.Call
import retrofit2.Response

const val SET = "set"
class ViewCollectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewCollectionBinding
    private var sets = mutableListOf<Set>()
    private lateinit var collectionRecyclerViewAdapter: CollectionRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityViewCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
        loadSets()
    }
    private fun setUpView() {
        binding.apply {
            collectionRecyclerViewAdapter = CollectionRecyclerViewAdapter(sets) {
                val intent = Intent(this@ViewCollectionActivity, ViewCardsActivity::class.java)
                intent.putExtra(SET, it)
                startActivity(intent)
            }
            collectionRecyclerView.layoutManager = LinearLayoutManager(this@ViewCollectionActivity)
            collectionRecyclerView.adapter = collectionRecyclerViewAdapter
        }
    }
    private fun loadSets() {
        Network.api.getSets().enqueue(
            object : retrofit2.Callback<BaseSetResponse> {
                override fun onResponse(
                    call: Call<BaseSetResponse>,
                    response: Response<BaseSetResponse>
                ) {
                    if (response.isSuccessful) {
                        sets.clear()
                        val responseBody = response.body()
                        responseBody?.let { baseSetResponse ->
                            baseSetResponse.data.let { sets.addAll(it) }
                            collectionRecyclerViewAdapter.notifyDataSetChanged()
                        } ?: {
                            Toast.makeText(
                                this@ViewCollectionActivity,
                                "Error loading houses",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@ViewCollectionActivity,
                            "Error loading houses",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<BaseSetResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}