package uz.gita.examdoctor.presentation.pager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import uz.gita.examdoctor.R
import uz.gita.examdoctor.databinding.ScreenPagerBinding
import uz.gita.examdoctor.presentation.adapter.ViewPagerAdapter

class ViewPagerScreen : Fragment(R.layout.screen_pager) {

    private val binding by viewBinding(ScreenPagerBinding::bind)

    private val vp: ViewPager2 by lazy { binding.viewPager }
    private val buttonNext by lazy { binding.btnNext }
    private val dots: DotsIndicator by lazy { binding.dots }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = ViewPagerAdapter()

        vp.adapter = adapter
        dots.attachTo(vp)

        vp.registerOnPageChangeCallback(ob)

        binding.btnNext.setOnClickListener {
            if (vp.currentItem < 2)
                vp.currentItem = vp.currentItem + 1
            else {
                val action =
                    ViewPagerScreenDirections.actionViewPagerScreenToProfileScreen("+998901234567")
                findNavController().navigate(action)
            }
        }
    }

    private val ob = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            if (position < 2) {
                buttonNext.text = "Next"
            } else {
                vp.isUserInputEnabled = false
                buttonNext.text = "Get started"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        vp.unregisterOnPageChangeCallback(ob)
    }
}

