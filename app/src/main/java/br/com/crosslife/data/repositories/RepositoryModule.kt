package br.com.crosslife.data.repositories

import br.com.crosslife.core.network.services.UserService
import br.com.crosslife.core.network.services.WeeklyTrainService
import br.com.crosslife.domain.repositories.UserRepository
import br.com.crosslife.data.repositories.user.UserRepositoryImpl
import br.com.crosslife.data.repositories.weeklytrain.WeeklyTrainRepositoryImpl
import br.com.crosslife.domain.preferences.UserStore
import br.com.crosslife.domain.repositories.WeeklyTrainRepository
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

}