package br.com.yujiyoshimine.data.repositories

import br.com.yujiyoshimine.data.repositories.notice.NoticeRepositoryImpl
import br.com.yujiyoshimine.data.repositories.user.UserRepositoryImpl
import br.com.yujiyoshimine.data.repositories.weeklytrain.WeeklyTrainRepositoryImpl
import br.com.yujiyoshimine.domain.repository.NoticeRepository
import br.com.yujiyoshimine.domain.repository.UserRepository
import br.com.yujiyoshimine.domain.repository.WeeklyTrainRepository
import br.com.yujiyoshimine.domain.store.UserStore
import br.com.yujiyoshimine.network.services.NoticeService
import br.com.yujiyoshimine.network.services.UserService
import br.com.yujiyoshimine.network.services.WeeklyTrainService
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
    ): WeeklyTrainRepository = WeeklyTrainRepositoryImpl(weeklyTrainService)

    @ViewModelScoped
    @Provides
    fun provideNoticeRepository(
        noticeService: NoticeService,
    ): NoticeRepository = NoticeRepositoryImpl(noticeService)
}