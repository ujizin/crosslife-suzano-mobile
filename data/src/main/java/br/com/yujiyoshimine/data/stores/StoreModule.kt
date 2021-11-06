package br.com.yujiyoshimine.data.stores

import br.com.crosslife.data.stores.user.UserStoreImpl
import br.com.yujiyoshimine.domain.store.UserStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object StoreModule {

    @Provides
    fun provideUserStore(dataStore: DataPreferences): UserStore = UserStoreImpl(dataStore)
}