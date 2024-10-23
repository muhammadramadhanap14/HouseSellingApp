package com.example.housesellingapp

data class Listing(
    val id: Int,
    val project_name: String,
    val address: Address,
    val attributes: Attributes,
    val photo: String
)

data class Address(
    val district: String,
    val street_name: String
)

data class Attributes(
    val area_size: Int,
    val bathrooms: Int,
    val bedrooms: Int,
    val price: Int,
    val pricePerSquareFoot: Double, 
    val floorLevel: String 
)

data class ListingDetail(
    val id: Int,
    val project_name: String,
    val address: AddressDetail,
    val attributes: Attributes,
    val description: String,
    val photo: String,
    val property_details: List<PropertyDetail>
)

data class AddressDetail(
    val title: String,
    val subtitle: String,
    val map_coordinates: MapCoordinates
)

data class MapCoordinates(
    val lat: Double,
    val lng: Double
)

data class PropertyDetail(
    val label: String,
    val text: String
)
