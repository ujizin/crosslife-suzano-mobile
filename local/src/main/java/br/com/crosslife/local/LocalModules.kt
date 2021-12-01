package br.com.crosslife.local

import android.content.Context
import androidx.room.Room
import br.com.crosslife.local.dao.NoticeDao
import br.com.crosslife.local.dao.TrainDao
import br.com.crosslife.local.datapreferences.DataPreferences
import br.com.crosslife.local.datapreferences.DataPreferencesImpl
import br.com.crosslife.local.store.notice.NoticeStore
import br.com.crosslife.local.store.notice.NoticeStoreImpl
import br.com.crosslife.local.store.notification.NotificationStore
import br.com.crosslife.local.store.notification.NotificationStoreImpl
import br.com.crosslife.local.store.train.TrainStore
import br.com.crosslife.local.store.train.TrainStoreImpl
import br.com.crosslife.local.store.user.UserStore
import br.com.crosslife.local.store.user.UserStoreImpl
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
    internal fun provideTrainDao(db: AppDatabase): TrainDao = db.trainDao()

    @Singleton
    @Provides
    internal fun provideNoticeDao(db: AppDatabase): NoticeDao = db.noticeDao()

    @Singleton
    @Provides
    fun provideTrainStore(trainDao: TrainDao): TrainStore = TrainStoreImpl(trainDao)

    @Singleton
    @Provides
    fun provideNoticeStore(noticeDao: NoticeDao): NoticeStore = NoticeStoreImpl(noticeDao)

    @Singleton
    @Provides
    fun provideUserStore(dataStore: DataPreferences): UserStore = UserStoreImpl(dataStore)

    @Singleton
    @Provides
    fun provideNotificationStore(dataStore: DataPreferences): NotificationStore =
        NotificationStoreImpl(dataStore)

    @Singleton
    @Provides
    @Named("user_token")
    fun provideUserToken(userStore: UserStore) = userStore.getToken()
}
