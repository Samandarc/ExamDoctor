package uz.gita.examdoctor.data.common

import android.net.Uri

data class ProfileData(
    val fullName: String,
    val nickname: String,
    val dateOfBirth: String,
    val email: String,
    val phone: String,
    val gender: String,
    val imageUri: Uri? = null,
    val imageUrl:String = ""
)
