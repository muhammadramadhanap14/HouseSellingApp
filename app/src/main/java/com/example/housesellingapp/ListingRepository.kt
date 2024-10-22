package com.example.housesellingapp

import javax.inject.Inject

class ListingRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getSearchResults() = apiService.getSearchResults()
    suspend fun getListingDetail(id: Int) = apiService.getListingDetail(id)
}