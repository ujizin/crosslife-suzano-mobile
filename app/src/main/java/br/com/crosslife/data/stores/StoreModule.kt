package br.com.crosslife.data.stores

import br.com.crosslife.core.local.datapreferences.DataPreferences
import br.com.crosslife.data.stores.user.UserStoreImpl
import br.com.crosslife.domain.preferences.UserStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.scopes.ServiceScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object StoreModule {

    @Provides
    fun provideUserStore(dataStore: DataPreferences): UserStore = UserStoreImpl(dataStore)
}