package br.com.crosslife.core.local

import android.content.Context
import androidx.room.Room
import br.com.crosslife.core.local.dao.TrainDao
import br.com.crosslife.core.local.datapreferences.DataPreferences
import br.com.crosslife.core.local.datapreferences.DataPreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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

}
