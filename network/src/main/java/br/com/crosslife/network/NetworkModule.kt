package br.com.crosslife.network

import br.com.crosslife.network.services.NoticeService
import br.com.crosslife.network.services.UserService
import br.com.crosslife.network.services.WeeklyTrainService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private fun okHttpClient(token: Flow<String>) = OkHttpClient.Builder()
        .addInterceptor {
            runBlocking {
                val request = it.request().newBuilder()
                    .addToken(token.first())
                    .build()
                it.proceed(request)
            }
        }.build()

    private fun Request.Builder.addToken(token: String) = apply {
        if (token.isNotBlank()) {
            addHeader("Authorization", "Bearer $token")
        }
    }

    private inline fun <reified T> newInstance(retrofit: Retrofit): T {
        return retrofit.create(T::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(@Named("user_token") token: Flow<String>): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient(token))
            .build()

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService = newInstance(retrofit)

    @Singleton
    @Provides
    fun provideWeeklyTrainService(retrofit: Retrofit): WeeklyTrainService = newInstance(retrofit)

    @Singleton
    @Provides
    fun provideNoticeService(retrofit: Retrofit): NoticeService = newInstance(retrofit)
}
