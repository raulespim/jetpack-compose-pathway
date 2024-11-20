Amphibians app
==================================

Code for Android Basics with Compose Practice Set.

Pre-requisites
--------------

You need to know:
- How to create Composable functions.
- How to use architecture components including ViewModel.
- How to use [Retrofit](https://square.github.io/retrofit/) to retrieve data from the internet.
- How to use [Coil](https://coil-kt.github.io/coil/) to load images.

What you'll build
--------------

In this practice set, you will build an app to display a list of amphibians, along with their details and image. 
The data is retrieved from the internet by making a network request and contains each amphibian's name, type, description, and image URL.

The amphibian JSON data is hosted at https://android-kotlin-fun-mars-server.appspot.com/amphibians.

Set up dependencies
--------------

The app uses Retrofit for network requests, Coil for image loading, and the kotlinx.serialization library for parsing the JSON returned by the Amphibians API.

This layer contains the ViewModel and composable functions that display the UiState coming from the ViewModel on the screen. The ViewModel is in charge of exposing the screen UI state, and handling the business logic in the UI layer and calling the business logic from other layers of the hierarchy.

UI Layer
--------------

The UI layer also contains the visual elements that your users see and interact with. 
This layer is where you decide how the various elements go together to create the UI you envision. 
You are deciding on the colors, fonts, and how you display images.

Data Layer
--------------

The data layer is responsible for retrieving the amphibian data from the API.

You probably want to include a data class for the amphibian data, a repository to manage the data, and a data source class to retrieve the data from the network.

If you need some help making the network calls, you can refer to Web services and Retrofit from the Get data from the internet codelab.

For help parsing the network response, refer to Parse the JSON response with kotlinx.serialization.

For loading images with Coil, you can check out the official documentation or refer back to the Display a downloaded image section of the Load and display images from the Internet codelab.