package com.example.housesellingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.housesellingapp.databinding.ActivitySearchResultsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchResultsBinding
    private val viewModel: ListingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil data listing
        viewModel.fetchSearchResults()
        viewModel.listings.observe(this) { listings ->
            if (listings.isNullOrEmpty()) {
                // Tangani kasus di mana data listings kosong atau null
                showErrorMessage("No listings available.")
                Log.e("SearchResultsActivity", "Listings data is empty or null.")
            } else {
                // Mengatur adapter jika data tidak null
                binding.rvListings.adapter = ListingAdapter(listings) { id ->
                    val intent = Intent(this, ListingDetailActivity::class.java)
                    intent.putExtra("listing_id", id)
                    startActivity(intent)
                }
                Log.d("SearchResultsActivity", "Listings loaded successfully.")
            }
        }
    }

    private fun showErrorMessage(message: String) {
        // Menampilkan pesan kesalahan menggunakan Toast
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}