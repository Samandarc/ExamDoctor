package uz.gita.examdoctor.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.examdoctor.data.common.ProfileData
import uz.gita.examdoctor.domain.repository.ProfileRepository
import uz.gita.examdoctor.domain.usecase.ProfileUseCase
import javax.inject.Inject

class ProfileUseCaseImpl @Inject constructor(private val repository: ProfileRepository) :
    ProfileUseCase {
    override fun addProfile(profileData: ProfileData): Flow<Result<Unit>> =
        repository.addProfile(profileData)
}