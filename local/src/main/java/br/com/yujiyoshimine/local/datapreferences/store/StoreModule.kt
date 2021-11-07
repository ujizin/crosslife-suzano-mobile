package br.com.yujiyoshimine.local.datapreferences.store

import br.com.yujiyoshimine.domain.store.UserStore
import br.com.yujiyoshimine.local.datapreferences.DataPreferences
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