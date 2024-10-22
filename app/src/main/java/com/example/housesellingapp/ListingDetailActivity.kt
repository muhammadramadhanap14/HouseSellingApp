package com.example.housesellingapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.housesellingapp.databinding.ActivityListingDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListingDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListingDetailBinding
    private val viewModel: ListingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListingDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil ID dari intent
        val listingId = intent.getIntExtra("listing_id", -1)

        // Menampilkan detail listing berdasarkan ID
        if (listingId != -1) {
            viewModel.fetchListingDetail(listingId)
            viewModel.listingDetail.observe(this) { listingDetail ->
                listingDetail?.let {
                    // Update UI dengan detail listing
                    updateUI(it)
                } ?: run {
                    // Tangani kasus di mana listingDetail null
                    showErrorMessage("Detail listing tidak tersedia.")
                    Log.e("ListingDetailActivity", "Listing detail is null for ID: $listingId")
                }
            }
        } else {
            showErrorMessage("ID listing tidak valid.")
            Log.e("ListingDetailActivity", "Invalid listing ID received: $listingId")
        }

        // Set up back button listener
        binding.backButton.setOnClickListener {
            onBackPressed() // Mengembalikan ke aktivitas sebelumnya
        }
    }

    private fun updateUI(listingDetail: ListingDetail) {
        // Menampilkan gambar dengan Glide
        Glide.with(this)
            .load(listingDetail.photo)
            .into(binding.propertyImage)

        // Update TextView dengan data properti
        binding.propertyPrice.text = "$${listingDetail.attributes.price ?: "N/A"}"
        binding.propertyName.text = listingDetail.address.title ?: "N/A"
        binding.propertyLocation.text = listingDetail.address.subtitle ?: "N/A"
        binding.propertyAttributes.text = "${listingDetail.attributes.bedrooms ?: "No description available."} Bedrooms, " +
                "${listingDetail.attributes.bathrooms ?: 0} Bathrooms, " +
                "${listingDetail.attributes.area_size ?: 0} sqft"

        // Mengupdate detail properti
        binding.propertyDetailPricePerSqft.text = "Price/sqft: $${listingDetail.attributes.pricePerSquareFoot ?: "N/A"} psf"
        binding.propertyDetailFloorLevel.text = "Floor Level: ${listingDetail.attributes.floorLevel ?: "N/A"}"

        // Menampilkan deskripsi properti
        binding.propertyDescription.text = listingDetail.description ?: "No description available."
    }

    private fun showErrorMessage(message: String) {
        // Menampilkan pesan kesalahan
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
