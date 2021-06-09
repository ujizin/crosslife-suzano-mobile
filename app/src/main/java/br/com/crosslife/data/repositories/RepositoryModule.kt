package br.com.crosslife.data.repositories

import br.com.crosslife.core.network.services.UserService
import br.com.crosslife.data.repositories.user.UserRepository
import br.com.crosslife.data.repositories.user.UserRepositoryImpl
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
    fun provideUserRepository(userService: UserService): UserRepository =
        UserRepositoryImpl(userService)
}