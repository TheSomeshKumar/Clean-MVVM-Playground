package com.example.playground.di

import android.content.Context
import com.example.playground.Constants
import com.example.playground.data.datasource.local.LocalDataSource
import com.example.playground.data.datasource.local.dao.PlaygroundDao
import com.example.playground.data.datasource.local.db.PlaygroundDatabase
import com.example.playground.data.datasource.remote.ApiServices
import com.example.playground.data.datasource.remote.RemoteDataSourceImpl
import com.example.playground.data.repository.PlaygroundRepositoryImpl
import com.example.playground.domain.repository.PlaygroundRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): PlaygroundDatabase = PlaygroundDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideBookingDao(db: PlaygroundDatabase): PlaygroundDao = db.dao()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().client(okHttpClient).baseUrl(Constants.TMDB_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideDefaultOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder().callTimeout(30, TimeUnit.SECONDS).connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).addInterceptor(loggingInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideApiServices(retrofitClient: Retrofit): ApiServices {
        return retrofitClient.create(ApiServices::class.java)
    }

    @Provides
    @Singleton
    fun provideAppRepository(
        api: ApiServices, localDataSource: LocalDataSource
    ): PlaygroundRepository {
        val remoteDataSourceImpl = RemoteDataSourceImpl(api)
        return PlaygroundRepositoryImpl(remoteDataSourceImpl, localDataSource = localDataSource)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(playgroundDao: PlaygroundDao): LocalDataSource {
        return LocalDataSource(playgroundDao)
    }
}
