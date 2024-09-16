# Fetch-Rewards-Coding-Exercise

## Overview

The **Fetch-Rewards-Coding-Exercise** is an Android application that demonstrates how to fetch and display a list of items from a remote JSON source. The application is built using Kotlin and adheres to the Model-View-Controller (MVC) architecture.

## Features


- Displays items in a list, grouped by `listId`.
- Sorts items by `listId` and then by `name`.
- Filters out items with blank or null names.
- Handles errors.

## Architecture

The project follows the MVC architecture pattern:

- **Model**: Defines the data structure (`Item`).
- **View**: Handles UI components and displays the data.
- **Controller**: Manages data fetching, processing, and updates the view.

## Dependencies

- [OkHttp](https://square.github.io/okhttp/): For handling HTTP requests.
- [Gson](https://github.com/google/gson): For JSON parsing.
