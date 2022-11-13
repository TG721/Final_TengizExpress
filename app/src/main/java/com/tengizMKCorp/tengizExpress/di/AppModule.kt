package com.tengizMKCorp.tengizExpress.di

import android.content.Context
import androidx.room.Room
import com.tengizMKCorp.tengizExpress.data.local.source.product.CartDao
import com.tengizMKCorp.tengizExpress.data.local.source.product.ProductDao
import com.tengizMKCorp.tengizExpress.data.local.source.product.ProductDatabase
import com.tengizMKCorp.tengizExpress.data.remote.MyInterceptor
import com.tengizMKCorp.tengizExpress.data.remote.StoreApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val client = OkHttpClient.Builder().apply {
        readTimeout(20,TimeUnit.SECONDS)
        addInterceptor(MyInterceptor())
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://magic-aliexpress1.p.rapidapi.com/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMyApi(retrofit: Retrofit): StoreApi {
        return retrofit.create(StoreApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductDatabase(@ApplicationContext context: Context): ProductDatabase {
        return Room.databaseBuilder(
            context,
            ProductDatabase::class.java, "product_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductDao(database: ProductDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    @Singleton
    fun provideCartDao(database: ProductDatabase): CartDao {
        return database.cartDao()
    }

//    @Provides
//    @Singleton
//    fun provideNameDatabase(@ApplicationContext context: Context) : NameDatabase {
//        return Room.databaseBuilder(
//            context,
//            NameDatabase::class.java, "product_database"
//        ).build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideNameDao(database: NameDatabase) : NameDao {
//        return database.nameDao()
//    }

}