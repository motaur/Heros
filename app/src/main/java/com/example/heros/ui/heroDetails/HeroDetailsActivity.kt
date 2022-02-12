package com.example.heros.ui.heroDetails

import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.heros.Constants
import com.example.heros.R
import com.example.heros.databinding.ActivityHeroDetailsBinding
import com.example.heros.models.HeroUiModel
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream


class HeroDetailsActivity : AppCompatActivity() {
    private val binding by lazy {ActivityHeroDetailsBinding.inflate(layoutInflater)}
        val vm: HeroDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewModel = vm
        binding.lifecycleOwner = this

        vm.heroUiModel = intent.getSerializableExtra(Constants.Keys.HERO_MODEL)!! as HeroUiModel

        binding.share.setOnClickListener {
            shareHeroDetails()
        }
    }

    fun shareHeroDetails() {
        val file = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "picToShare.jpeg")
        val stream = FileOutputStream(file)
        val bitMap = runBlocking { vm.getBitmap() }
        bitMap.compress(Bitmap.CompressFormat.JPEG, 90, stream)
        stream.close()
        val uri = FileProvider.getUriForFile(
            this,
            "$packageName.provider",
            file
        )
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TITLE, getString(R.string.my_hero))
            putExtra(Intent.EXTRA_TEXT, getString(R.string.my_hero) + ": "+ vm.heroUiModel.name)
            putExtra(Intent.EXTRA_STREAM, uri)
            setDataAndType(uri, "image/jpeg")
            flags = FLAG_GRANT_READ_URI_PERMISSION
        }
        val shareIntent = Intent.createChooser(sendIntent, getString(R.string.my_hero))
        startActivity(shareIntent)
    }


}