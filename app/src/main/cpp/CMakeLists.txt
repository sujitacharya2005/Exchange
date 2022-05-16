cmake_minimum_required(VERSION 3.10.2)

project("exchange")


add_library( # Sets the name of the library.
        exchange

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        crypto/native-lib.cpp crypto/ExchangeLog.h crypto/ExchangeLog.cpp)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        exchange

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})

set(GOOGLETEST_ROOT ${ANDROID_NDK}/sources/third_party/googletest)
add_library(gtest STATIC ${GOOGLETEST_ROOT}/src/gtest_main.cc ${GOOGLETEST_ROOT}/src/gtest-all.cc)
target_include_directories(gtest PRIVATE ${GOOGLETEST_ROOT})
target_include_directories(gtest PUBLIC ${GOOGLETEST_ROOT}/include)

add_executable(exchange_unitTest test/crypto/ExchangeLogTest.cpp)
target_link_libraries(exchange_unitTest gtest exchange)