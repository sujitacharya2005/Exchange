# Crypto Exchange App :

# Screenshots:

![image](https://user-images.githubusercontent.com/32419898/168638719-033aa4b4-a3b5-4c7f-b3f7-71bef9275bca.png). 
![image](https://user-images.githubusercontent.com/32419898/168638907-b8a9df30-e75d-4b43-8f13-1dd88272c0f0.png)

# Project Structure :

1. **app/src/main/java**      : Contains all kotlin source files
2. **app/src/main/cpp**       : Contains all native cpp source files
3. **app/src/main/cpp/test**  : Contains cpp unit test files
4. **app/src/test**           : Contains kotlin unit test files

# Architecture :
MVVM (Model - ViewModel - View) is the design pattern used for making this app. 
MVVM provides a clear separation of concern and has great support in Android SDK in the form of Architecture Components.

# Libraries Used
* **Architecture** - A collection of libraries that help you design robust, testable, and maintainable apps.
  * **Data Binding** - Declaratively bind observable data to UI elements.
  * **LiveData** - Build data objects that notify views when the underlying database changes.
  * **Room** - Access your app's SQLite database with in-app objects and compile-time checks.
  * **ViewModel** - Store UI-related data that isn't destroyed on app rotations. Easily schedule asynchronous tasks for optimal execution.
* **Third party**
  * **Glide** for image loading
  * **Kotlin** Coroutines for managing background threads with simplified code and reducing needs for callbacks
  * **Retrofit** for making HTTP requests
  * **Mockk** libary for unit testing
  * **NDK** for writing native code
  * **GOOGLETEST_ROOT** libary for cpp unit test

# Adb Command to unittest cpp
1. After build code go to app/build/intermediates/cmake/debug/obj/arm64-v8a/**
2. adb shell push * /data/local/tmp/
3. adb shell chmod 775 /data/local/tmp/exchange_unitTest
4. adb shell "LD_LIBRARY_PATH=/data/local/tmp /data/local/tmp/exchange_unitTest"

## TODO ##
*. private const val COINS_IDS = "bitcoin,binancecoin,ethereum,basic-attention-token"
*. private const val CURRENCIES = "usd,btc"

1. Currenlty these are hard coded later it's can improve fetch all coins and currencies from api
2. Improve code by using Hilt or dagger dependency
3. Convert UI code from xml format to Jetpack compose



