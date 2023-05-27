package uz.gita.examdoctor.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.examdoctor.data.common.ProfileData

interface

ProfileRepository {
    fun addProfile(profileData: ProfileData): Flow<Result<Unit>>
}