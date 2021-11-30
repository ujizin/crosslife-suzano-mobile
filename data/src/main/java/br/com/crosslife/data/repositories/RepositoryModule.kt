package br.com.crosslife.data.repositories

import br.com.crosslife.data.repositories.chat.ChatRepositoryImpl
import br.com.crosslife.data.repositories.conversation.ConversationRepositoryImpl
import br.com.crosslife.data.repositories.notice.NoticeRepositoryImpl
import br.com.crosslife.data.repositories.user.UserRepositoryImpl
import br.com.crosslife.data.repositories.weeklytrain.WeeklyTrainRepositoryImpl
import br.com.crosslife.domain.repository.*
import br.com.crosslife.local.store.notice.NoticeStore
import br.com.crosslife.local.store.train.TrainStore
import br.com.crosslife.local.store.user.UserStore
import br.com.crosslife.network.services.NoticeService
import br.com.crosslife.network.services.UserService
import br.com.crosslife.network.services.WeeklyTrainService
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

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

    @ExperimentalCoroutinesApi
    @ViewModelScoped
    @Provides
    fun provideConversationRepository(
        userStore: UserStore
    ): ConversationRepository = ConversationRepositoryImpl(
        userStore,
        FirebaseDatabase.getInstance().reference,
    )

    @ExperimentalCoroutinesApi
    @ViewModelScoped
    @Provides
    fun provideChatRepository(userStore: UserStore): ChatRepository = ChatRepositoryImpl(
        userStore,
        FirebaseDatabase.getInstance().reference,
    )
}