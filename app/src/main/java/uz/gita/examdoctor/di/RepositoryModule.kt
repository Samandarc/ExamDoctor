package uz.gita.examdoctor.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.examdoctor.domain.repository.ProfileRepository
import uz.gita.examdoctor.domain.repository.impl.ProfileRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindRepository(impl: ProfileRepositoryImpl): ProfileRepository
}