package br.com.crosslife.core.network

import br.com.crosslife.BuildConfig
import br.com.crosslife.core.network.services.UserService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val okHttpClient get() = OkHttpClient.Builder().build()

    private inline fun <reified T> newInstance(retrofit: Retrofit): T {
        return retrofit.create(T::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService = newInstance(retrofit)
}
