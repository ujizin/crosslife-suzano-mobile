package br.com.crosslife.core.local

import android.content.Context
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

    @Singleton
    @Provides
    fun provideDataPreferences(@ApplicationContext context: Context): DataPreferences =
        DataPreferencesImpl(context)
}
