package com.example.housesellingapp

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("listings.json") 
    suspend fun getSearchResults(): List<Listing>?

    @GET("details/{id}.json") 
    suspend fun getListingDetail(@Path("id") id: Int): ListingDetail?
}
