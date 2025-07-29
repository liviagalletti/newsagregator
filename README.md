# Overview

This project is a news aggregator app designed to fetch and display the latest news headlines from a public API. The goal is to build a practical Android application that consumes REST APIs, handles asynchronous data loading, and displays dynamic content efficiently using RecyclerView.

Users can load current top news headlines by tapping the "Load News" button. The app will then fetch news articles from the NewsAPI.org service and display them in a scrollable list, showing the article’s title and image. Clicking on a news item opens the full article in the device’s browser.

The purpose of creating this app is to deepen my skills in Android development with Kotlin, Retrofit for networking, and UI design using RecyclerView. It also serves as practice for integrating third-party APIs and managing asynchronous calls.

[Software Demo Video](http://youtube.link.goes.here)

# Development Environment

The app was developed using Android Studio, the official IDE for Android development. The primary programming language used is Kotlin, which offers concise syntax and modern language features.

Key libraries used in the project include:
- Retrofit: For making HTTP requests and parsing JSON responses.
- OkHttp: To add network interceptors and handle HTTP client configuration.
- Glide: To efficiently load and cache images from URLs.
- AndroidX RecyclerView: To display the list of news articles in a performant and flexible way.

# Useful Websites

* [NewsAPI.org](https://newsapi.org) – Official documentation for the news API used.
* [Retrofit Official Documentation](https://square.github.io/retrofit/) – Guide for Retrofit library usage.
* [Glide GitHub Repository](https://github.com/bumptech/glide) – For image loading best practices.
* [Android Developer Documentation](https://developer.android.com) – General Android development resources.

# Future Work

* Add support for multiple news categories and allow users to filter by topic.
* Implement pagination to load more news articles as the user scrolls.
* Improve error handling with user-friendly messages when the network fails.
* Add pull-to-refresh functionality for easier content updates.
* Integrate local caching so that users can see news offline.
