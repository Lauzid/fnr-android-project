package com.example.fnr_android_project.ui

import android.annotation.SuppressLint
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
import com.example.fnr_android_project.network.Network
import com.example.fnr_android_project.ui.collection_recycler_view.CollectionRecyclerViewAdapter
import com.example.fnr_android_project.model.Set

const val SET = "set"
class ViewCollectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewCollectionBinding
    private var sets = mutableListOf<Set>()
    private var collectionRecyclerViewAdapter: CollectionRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityViewCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpView()
        loadSets()
    }

    private fun setUpView() {
        binding.apply {
            collectionRecyclerView.layoutManager = LinearLayoutManager(this@ViewCollectionActivity)
            collectionRecyclerView.adapter = collectionRecyclerViewAdapter
        }
    }

    private fun loadSets() {
        Network.api.getSets().enqueue(
            object : retrofit2.Callback<List<Set>> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: retrofit2.Call<List<Set>>,
                    response: retrofit2.Response<List<Set>>
                ) {
                    if (response.isSuccessful) {
                        sets.clear()
                        response.body()?.let {
                            sets.addAll(it)
                            collectionRecyclerViewAdapter = CollectionRecyclerViewAdapter(sets) {
                                val intent = Intent(this@ViewCollectionActivity, ViewCardsActivity::class.java)
                                intent.putExtra(SET, it)
                                startActivity(intent)
                            }
                            collectionRecyclerViewAdapter?.notifyDataSetChanged()
                            binding.collectionRecyclerView.adapter = collectionRecyclerViewAdapter
                        }
                    } else {
                        Toast.makeText(
                            this@ViewCollectionActivity,
                            "Error loading data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: retrofit2.Call<List<Set>>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(
                        this@ViewCollectionActivity,
                        "Error loading data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}