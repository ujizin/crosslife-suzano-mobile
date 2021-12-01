package br.com.crosslife

import br.com.crosslife.domain.manager.PushManager
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
    fun providePushManager(): PushManager = CrossLifeMessageService.Companion
}