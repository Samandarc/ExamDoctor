package uz.gita.examdoctor.presentation.screen.fillprofile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.examdoctor.R
import uz.gita.examdoctor.databinding.ScreenProfileBinding
import uz.gita.examdoctor.presentation.screen.fillprofile.viewmodel.ProfileViewModelImpl

@AndroidEntryPoint
class ProfileScreen : Fragment(R.layout.screen_profile) {
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private val binding by viewBinding(ScreenProfileBinding::bind)
    private val viewModel by viewModels<ProfileViewModelImpl>()
    private var imageUri: Uri? = null
    private val args by navArgs<ProfileScreenArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel.openNextScreenLiveData.observe(this, openNextScreenLiveData)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {
            btnEdit.setOnClickListener {
                openGallery()
            }
            editFullName.doAfterTextChanged {
                check()
            }
            editDate.doAfterTextChanged {
                check()
            }
            editEmail.doAfterTextChanged {
                check()
            }
            editGenter.doAfterTextChanged {
                check()
            }

            btnContinue.setOnClickListener {
                viewModel.addProfile(
                    editFullName.text.toString(),
                    editNickName.text.toString(),
                    editDate.text.toString(),
                    editEmail.text.toString(),
                    args.phone,
                    editGenter.text.toString(),
                    imageUri
                )
            }
        }
        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { callback ->
                callback.data?.data?.let {
                    binding.avatar.setImageURI(it)
                    imageUri = it
                }
            }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Log.d("TTT", it)
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }

    private fun check() {
        binding.apply {
            if (editFullName.text.toString().isEmpty() ||
                editNickName.text.toString().isEmpty() ||
                editDate.text.toString().isEmpty() ||
                editEmail.text.toString().isEmpty() ||
                editGenter.text.toString().isEmpty()
            ) {
                btnContinue.isClickable = false
                btnContinue.isEnabled = false
            } else {
                btnContinue.isClickable = true
                btnContinue.isEnabled = true
            }
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }


    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        launcher.launch(intent)
    }

    private val openNextScreenLiveData = Observer<Unit> {
//        todo from profile to home
//         findNavController().navigate()
    }
}