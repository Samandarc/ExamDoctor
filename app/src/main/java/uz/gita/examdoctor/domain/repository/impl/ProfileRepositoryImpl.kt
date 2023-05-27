package uz.gita.examdoctor.domain.repository.impl

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import uz.gita.examdoctor.data.common.ProfileData
import uz.gita.examdoctor.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val storageReference: StorageReference
) : ProfileRepository {

    override fun addProfile(profileData: ProfileData): Flow<Result<Unit>> = callbackFlow {
        var url: Uri
        val urlAvatar = profileData.imageUri?.let {
            loadImage(it, "profile_image").onSuccess {
                val userData =
                    ProfileData(
                        profileData.fullName,
                        profileData.nickname,
                        profileData.dateOfBirth,
                        profileData.email,
                        profileData.phone,
                        profileData.gender,
                        it
                    )

                val collection = fireStore.collection("users")

                collection
                    .document(profileData.phone)
                    .set(userData)
                    .addOnSuccessListener {
                        trySend(Result.success(Unit))
                    }
                    .addOnFailureListener {
                        trySend(Result.failure(it))
                    }
            }
                .onFailure {
                    trySend(Result.failure(it))
                }
        }
        awaitClose()
    }

    private suspend fun loadImage(uriForImage: Uri, reference: String): Result<Uri> {
        try {
            val uploadTaskImage = storageReference.child(reference)
                .putFile(uriForImage)
                .await()

            return Result.success(uploadTaskImage.storage.downloadUrl.await())
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }


}