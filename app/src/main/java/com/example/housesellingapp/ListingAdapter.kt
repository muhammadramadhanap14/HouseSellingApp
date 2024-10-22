package com.example.housesellingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListingAdapter(private val listings: List<Listing>, private val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<ListingAdapter.ListingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_result, parent, false)
        return ListingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        val listing = listings[position]
        holder.bind(listing)
        holder.itemView.setOnClickListener { onClick(listing.id) }
    }

    override fun getItemCount() = listings.size

    class ListingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgPhoto: ImageView = view.findViewById(R.id.imgPhoto)
        private val tvProjectName: TextView = view.findViewById(R.id.tvProjectName)
        private val tvAddress: TextView = view.findViewById(R.id.tvAddress)
        private val tvDetails: TextView = view.findViewById(R.id.tvDetails)
        private val tvPrice: TextView = view.findViewById(R.id.tvPrice)

        fun bind(listing: Listing) {
            // Menggunakan Glide untuk menangani image yang mungkin null
            Glide.with(itemView.context).load(listing.photo ?: "").into(imgPhoto)

            // Cek apakah field bukan null, jika null beri nilai default
            tvProjectName.text = listing.project_name ?: "Unknown Project"
            val streetName = listing.address.street_name ?: "Unknown Street"
            val district = listing.address.district ?: "Unknown District"
            tvAddress.text = "$streetName, $district"

            // Menggunakan nilai default untuk price jika null
            tvPrice.text = "${listing.attributes.price ?: "N/A"}/mo"
        }
    }
}