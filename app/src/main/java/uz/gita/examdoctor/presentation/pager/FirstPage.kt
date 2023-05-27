package uz.gita.examdoctor.presentation.pager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.examdoctor.R

class FirstPage : Fragment(R.layout.screen_first_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        lifecycleScope.launch {
            delay(3000)
            findNavController().navigate(R.id.action_firstPage_to_viewPagerScreen)
        }
    }
}