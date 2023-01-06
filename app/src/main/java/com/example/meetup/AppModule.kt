package com.example.meetup

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.meetup.api.AuthApi
import com.example.meetup.api.FriendsApi
import com.example.meetup.authorization.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthApi(authInterceptor: AuthInterceptor): AuthApi {
        return RetrofitClient(authInterceptor).authApi
    }

    @Provides
    @Singleton
    fun provideFriendsApi(authInterceptor: AuthInterceptor): FriendsApi {
        return RetrofitClient(authInterceptor).friendsApi
    }

    @Provides
    @Singleton
    fun provideSharedPref(app: Application): SharedPreferences {
        return app.getSharedPreferences("prefs", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi, prefs: SharedPreferences): AuthRepository {
        return AuthRepositoryImpl(api, prefs)
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(prefs: SharedPreferences): AuthInterceptor {
        return AuthInterceptor(prefs)
    }
}