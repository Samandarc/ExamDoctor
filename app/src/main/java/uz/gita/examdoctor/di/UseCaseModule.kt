package uz.gita.examdoctor.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.examdoctor.domain.usecase.ProfileUseCase
import uz.gita.examdoctor.domain.usecase.impl.ProfileUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {
    @[Binds Singleton]
    fun bindUseCase(impl: ProfileUseCaseImpl): ProfileUseCase
}