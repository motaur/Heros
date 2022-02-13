package com.example.heroes.ui.heroDetails

import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import androidx.core.content.FileProvider
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.heroes.Constants
import com.example.heroes.R
import com.example.heroes.databinding.ActivityHeroDetailsBinding
import com.example.heroes.models.HeroUiModel
import com.example.heroes.ui.heroDetails.heroDetailsFragments.AppearanceFragment
import com.example.heroes.ui.heroDetails.heroDetailsFragments.ConnectionsFragment
import com.example.heroes.ui.heroDetails.heroDetailsFragments.WorkFragment
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream

class HeroDetailsActivity : FragmentActivity() {

    private val binding by lazy { ActivityHeroDetailsBinding.inflate(layoutInflater) }
    val vm: HeroDetailsViewModel by viewModel()
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewModel = vm
        binding.lifecycleOwner = this

        vm.heroUiModel = intent.getSerializableExtra(Constants.Keys.HERO_MODEL)!! as HeroUiModel

        viewPager = binding.pager

        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabs, viewPager) { _, _ -> }.attach()

        binding.share.setOnClickListener {
            shareHeroDetails()
        }
    }

    private enum class HeroCard {
        Appearance ,
        Work,
        Connections
    }

    private inner class ScreenSlidePagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int = HeroCard.values().size

        override fun createFragment(position: Int): Fragment {
            return when(HeroCard.values()[position]){
                HeroCard.Appearance -> AppearanceFragment(vm.heroUiModel.appearance)
                HeroCard.Work -> WorkFragment(vm.heroUiModel.work)
                HeroCard.Connections -> ConnectionsFragment(vm.heroUiModel.connections)
            }
        }
    }

    private fun shareHeroDetails() {
        val file = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "picToShare.jpeg")
        val stream = FileOutputStream(file)
        val bitMap = binding.heroPhoto.drawToBitmap()
        bitMap.compress(Bitmap.CompressFormat.JPEG, 90, stream)
        stream.close()
        val uri = FileProvider.getUriForFile(
            this,
            "$packageName.provider",
            file
        )
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TITLE, getString(R.string.share_hero))
            putExtra(Intent.EXTRA_TEXT, getString(R.string.my_hero) + ": "+ vm.heroUiModel.name)
            putExtra(Intent.EXTRA_STREAM, uri)

            // TODO
            // in Gmail/Keep share Picture and text well, maybe need to add subject/title;
            // Not working properly for for Facebook and Instagram shares only picture due to Facebook restriction,
            // available only through facebook sdk
            setDataAndType(uri, "image/*" + "text/*")
            flags = FLAG_GRANT_READ_URI_PERMISSION
        }
        val shareIntent = Intent.createChooser(sendIntent, getString(R.string.my_hero))
        startActivity(shareIntent)
    }


}