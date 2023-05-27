package uz.gita.examdoctor.presentation.screen.fillprofile.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData

interface ProfileViewModel {
    val errorLiveData: LiveData<String>
    val openNextScreenLiveData: LiveData<Unit>
    fun addProfile(
        fullName: String,
        nickName: String,
        date: String,
        email: String,
        phone: String,
        gender: String,
        imgUri: Uri? = null
    )
}