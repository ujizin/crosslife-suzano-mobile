package br.com.crosslife.data.repositories

import br.com.crosslife.data.repositories.conversation.ConversationRepositoryImpl
import br.com.crosslife.data.repositories.notice.NoticeRepositoryImpl
import br.com.crosslife.data.repositories.user.UserRepositoryImpl
import br.com.crosslife.data.repositories.weeklytrain.WeeklyTrainRepositoryImpl
import br.com.crosslife.domain.repository.ConversationRepository
import br.com.crosslife.domain.repository.NoticeRepository
import br.com.crosslife.domain.repository.UserRepository
import br.com.crosslife.domain.repository.WeeklyTrainRepository
import br.com.crosslife.local.store.notice.NoticeStore
import br.com.crosslife.local.store.train.TrainStore
import br.com.crosslife.local.store.user.UserStore
import br.com.crosslife.network.services.NoticeService
import br.com.crosslife.network.services.UserService
import br.com.crosslife.network.services.WeeklyTrainService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideUserRepository(
        userService: UserService,
        userStore: UserStore,
    ): UserRepository = UserRepositoryImpl(userService, userStore)

    @ViewModelScoped
    @Provides
    fun provideWeeklyTrainRepository(
        weeklyTrainService: WeeklyTrainService,
        trainStore: TrainStore
    ): WeeklyTrainRepository = WeeklyTrainRepositoryImpl(weeklyTrainService, trainStore)

    @ViewModelScoped
    @Provides
    fun provideNoticeRepository(
        noticeService: NoticeService,
        noticeStore: NoticeStore
    ): NoticeRepository = NoticeRepositoryImpl(noticeService, noticeStore)

    @ViewModelScoped
    @Provides
    fun provideConversationRepository(): ConversationRepository = ConversationRepositoryImpl()
}