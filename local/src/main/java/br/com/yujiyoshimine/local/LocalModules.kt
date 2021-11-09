package br.com.yujiyoshimine.local

import android.content.Context
import androidx.room.Room
import br.com.yujiyoshimine.local.dao.TrainDao
import br.com.yujiyoshimine.local.datapreferences.DataPreferences
import br.com.yujiyoshimine.local.datapreferences.DataPreferencesImpl
import br.com.yujiyoshimine.local.datapreferences.store.UserStore
import br.com.yujiyoshimine.local.datapreferences.store.UserStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModules {

    private const val CROSSLIFE_DB = "crosslife_db"

    @Singleton
    @Provides
    fun provideDataPreferences(@ApplicationContext context: Context): DataPreferences =
        DataPreferencesImpl(context)

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            CROSSLIFE_DB
        ).build()

    @Singleton
    @Provides
    fun provideTrainDao(db: AppDatabase): TrainDao = db.trainDao()

    @Singleton
    @Provides
    fun provideUserStore(dataStore: DataPreferences): UserStore = UserStoreImpl(dataStore)

    @Singleton
    @Provides
    @Named("user_token")
    fun provideUserToken(userStore: UserStore) = userStore.getToken()
}
