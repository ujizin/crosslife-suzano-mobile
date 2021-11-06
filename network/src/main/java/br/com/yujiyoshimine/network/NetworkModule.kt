package br.com.yujiyoshimine.network

import br.com.crosslife.BuildConfig
import br.com.yujiyoshimine.network.services.NoticeService
import br.com.yujiyoshimine.network.services.UserService
import br.com.yujiyoshimine.network.services.WeeklyTrainService
import br.com.yujiyoshimine.domain.store.UserStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    fun okHttpClient(userStore: UserStore): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                runBlocking {
                    val token = userStore.getToken().first()
                    val request = it.request().newBuilder()
                        .addHeader("Authorization", "Bearer $token")
                        .build()
                    it.proceed(request)
                }
            }.build()
    }

    private inline fun <reified T> newInstance(retrofit: Retrofit): T {
        return retrofit.create(T::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(dataStore: UserStore): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient(dataStore))
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
