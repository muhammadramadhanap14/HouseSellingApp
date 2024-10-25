package com.example.housesellingapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListingViewModel @Inject constructor(
private val repository: ListingRepository
) : ViewModel() {

    private val _listings = MutableLiveData<List<Listing>>()
    val listings: LiveData<List<Listing>> = _listings

    private val _listingDetail = MutableLiveData<ListingDetail?>()
    val listingDetail: LiveData<ListingDetail?> = _listingDetail

    fun fetchSearchResults() {
        viewModelScope.launch {
            try {
                val results = repository.getSearchResults()
                _listings.value = results ?: emptyList() // Jika null, set ke list kosong
            } catch (e: Exception) {
                _listings.value = emptyList() // Tangani exception dan set list kosong
                e.printStackTrace()
            }
        }
    }

    fun fetchListingDetail(id: Int) {
        viewModelScope.launch {
            try {
                val detail = repository.getListingDetail(id)
                Log.d("ListingViewModel", "Listing detail: $detail")
                _listingDetail.value = (detail ?: ListingDetailActivity()) as ListingDetail? // Menggunakan objek default jika null
            } catch (e: Exception) {
                _listingDetail.value = null
                e.printStackTrace()
            }
        }
    }
}
