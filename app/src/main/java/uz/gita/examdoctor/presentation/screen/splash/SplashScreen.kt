package uz.gita.examdoctor.presentation.screen.splash

import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.examdoctor.R
import uz.gita.examdoctor.databinding.ScreenSplashBinding

@SuppressLint("CustomSplashScreen")
class SplashScreen:Fragment(R.layout.screen_splash) {
    private val binding by viewBinding(ScreenSplashBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val animLoad = binding.imageView2.drawable as AnimationDrawable
        animLoad.start()
        lifecycleScope.launch {
            delay(1000)
            findNavController().navigate(R.id.action_splashScreen_to_firstPage)
        }
    }
}