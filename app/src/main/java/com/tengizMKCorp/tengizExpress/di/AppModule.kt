package com.tengizMKCorp.tengizExpress.di

import android.content.Context
import androidx.room.Room
import com.tengizMKCorp.tengizExpress.data.local.source.ProductDao
import com.tengizMKCorp.tengizExpress.data.local.source.ProductDatabase
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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit {
        return  Retrofit.Builder()
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
    fun provideDatabase(@ApplicationContext context: Context) : ProductDatabase {
        return Room.databaseBuilder(
            context,
            ProductDatabase::class.java, "product_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: ProductDatabase) : ProductDao {
        return database.productDao()
    }

}