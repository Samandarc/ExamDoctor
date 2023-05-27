package uz.gita.examdoctor.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.examdoctor.data.common.ProfileData

interface ProfileUseCase {
    fun addProfile(profileData: ProfileData): Flow<Result<Unit>>
}