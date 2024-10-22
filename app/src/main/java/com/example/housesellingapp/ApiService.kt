package com.example.housesellingapp

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("listings.json") // Hapus URL lengkap dan hanya gunakan path relatif
    suspend fun getSearchResults(): List<Listing>?

    @GET("details/{id}.json") // Pastikan untuk menggunakan path yang benar
    suspend fun getListingDetail(@Path("id") id: Int): ListingDetail?
}