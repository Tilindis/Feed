package com.ito.feed.utils.di

import com.ito.feed.utils.api.FeedsApi
import com.ito.feed.utils.constants.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideFeedsApiService(@Named(Constants.FEEDS_API_NAME) retrofit: Retrofit): FeedsApi =
        retrofit.create(FeedsApi::class.java)

    @Singleton
    @Provides
    @Named(Constants.FEEDS_API_NAME)
    fun provideRetrofitFeeds(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        createRetrofitBuilder(okHttpClient, moshi, Constants.FEEDS_URL)

    private fun createRetrofitBuilder(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        url: String
    ) = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(url)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}
