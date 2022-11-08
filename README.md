Aliexpress client App

This is a Aliexpressstore client app. at lunch it loads best sales items sorted by newest.
Use is also presented 4 menu, Item: Category, Search, Cart, Account.
Hone button is presented at the bottom of screen and always using whcih User can access Home Pag with the menu.


## Features

- Search with Search History
- Category
- Cart
- Swiping
- Choosing layout view after user selects category of products
- MVVM
- Networking with Retrofit
- Room
- Firebase Authentification
- Splash Screen

## Used Dependencies
    //glide
    implementation 'com.github.bumptech.glide:glide:4.13.2'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.13.2'
    //ImageSlideShow
    implementation 'com.github.denzcoskun:ImageSlideshow:0.1.0'
    //firebase
    implementation 'com.google.firebase:firebase-auth:21.1.0'
    implementation 'com.google.firebase:firebase-auth-ktx:21.1.0'
    //card view
    implementation "androidx.cardview:cardview:1.0.0"
    //recyclerview
    implementation "androidx.recyclerview:recyclerview:1.2.1"
    // ViewModel Compose
    implementation "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"
    //Dagger - Hilt
    implementation "com.google.dagger:hilt-android:2.43.2"
    kapt "com.google.dagger:hilt-compiler:2.43.2"
    // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation "com.squareup.okhttp3:logging-interceptor:4.10.0"
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.retrofit2:converter-moshi:2.9.0'
    // Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4'
    //moshi
    implementation "com.squareup.moshi:moshi:1.13.0"
    implementation "com.squareup.moshi:moshi-kotlin:1.12.0"
    // Room components
    def roomVer = "2.4.3"
    implementation "androidx.room:room-runtime:$roomVer"
    kapt "androidx.room:room-compiler:$roomVer"
    implementation "androidx.room:room-ktx:$roomVer"
    androidTestImplementation "androidx.room:room-testing:$roomVer"
    //Swipe Decorator
    implementation 'com.github.xabaras:RecyclerViewSwipeDecorator:1.4'
