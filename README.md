### Muhammad Ramadhan Aditya Putra (2602168772)  
**Universitas Bina Nusantara** - *Mobile Application and Technology*

---

# House Selling App

The **House Selling App** is an Android application that enables users to search for and view property listings with ease. The app is connected to a backend API, which provides data for both search results and detailed information on specific properties. Built using **Kotlin**, **MVVM architecture**, and **Dagger-Hilt** for dependency injection, this app ensures a clean and maintainable code structure.

---

## Features

- **Search Listings**: Users can browse property listings based on their search criteria, viewing them in a scrollable list format.
- **Property Details**: Each listing offers a detailed view, displaying crucial information such as price, address, and property attributes (e.g., number of bedrooms and bathrooms).
- **Image Loading**: Images are efficiently loaded and cached using **Glide**.
- **MVVM Architecture**: The app adheres to the Model-View-ViewModel architecture to promote a clear separation of concerns and streamline UI updates.

---

## Tech Stack

- **Kotlin**: Language for Android development.
- **MVVM (Model-View-ViewModel)**: Architecture to maintain separation between the UI and business logic.
- **Dagger-Hilt**: Framework for dependency injection, making the app modular and testable.
- **Coroutines**: Used for asynchronous operations and network requests.
- **Glide**: Manages image loading and caching.
- **RecyclerView**: Efficiently displays the property listings in a list.
- **LiveData**: Observes and reacts to data changes in the ViewModel, enabling seamless UI updates.

---

## Project Structure

```plaintext
├── data/
│   ├── ApiService.kt           # Interface for API requests
│   ├── ListingRepository.kt    # Repository that interacts with the ApiService
│
├── ui/
│   ├── ListingAdapter.kt       # RecyclerView adapter for displaying listings
│   ├── ListingViewModel.kt     # ViewModel for managing listing data
│   ├── MainActivity.kt         # Main entry point of the application
│   ├── SearchResultsActivity.kt # Activity to display search results
│   ├── ListingDetailActivity.kt # Activity to display detailed property info
│
├── di/
│   ├── NetworkModule.kt        # Hilt module for providing network dependencies
│
└── utils/
    └── GlideUtils.kt           # Utility for managing image loading with Glide
```

---

## Key Components

1. **ListingAdapter.kt**  
   Manages displaying property listings within a `RecyclerView`, utilizing **Glide** for image handling. Click listeners are set up to navigate users to the detailed view of a selected listing.

2. **ListingDetailActivity.kt**  
   Displays detailed property information, including price, address, and specific property features like bedrooms and bathrooms. Data is retrieved from the `ListingViewModel`.

3. **ListingViewModel.kt**  
   Manages data for listings and their details, fetching from the `ListingRepository`, which connects to the API. Observed by **LiveData**, the ViewModel updates the UI upon data changes.

4. **SearchResultsActivity.kt**  
   Fetches and displays search results from the API, observing the `listings` LiveData in the `ListingViewModel` to update the UI dynamically.

5. **MainActivity.kt**  
   Serves as the app’s entry point, setting up the initial content view and initializing essential components.

---

## Dependencies

- **Glide**: [GitHub Repository](https://github.com/bumptech/glide)
- **Dagger-Hilt**: [Dagger Documentation](https://dagger.dev/hilt)
- **Coroutines**: [Kotlin Coroutines Overview](https://kotlinlang.org/docs/coroutines-overview.html)
