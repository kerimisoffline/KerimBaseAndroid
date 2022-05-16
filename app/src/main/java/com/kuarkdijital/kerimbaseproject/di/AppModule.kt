package com.kuarkdijital.kerimbaseproject.di

import android.icu.util.CurrencyAmount
import com.kuarkdijital.kerimbaseproject.data.BaseApi
import com.kuarkdijital.kerimbaseproject.main.DefaultMainRepository
import com.kuarkdijital.kerimbaseproject.main.MainRepository
import com.kuarkdijital.kerimbaseproject.util.DispactherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

private const val BASE_URL = "https://api.coingecko.com/api/v3/"

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCurrencyApi():BaseApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BaseApi::class.java)

    @Singleton
    @Provides
    fun provideMainRepository(api:BaseApi): MainRepository = DefaultMainRepository(api)

    @Singleton
    @Provides
    fun provideDispatchers(): DispactherProvider = object : DispactherProvider{
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
}