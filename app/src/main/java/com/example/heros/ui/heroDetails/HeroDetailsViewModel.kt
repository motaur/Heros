package com.example.heros.ui.heroDetails

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.heros.App
import com.example.heros.R
import com.example.heros.models.HeroUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Suppress("BlockingMethodInNonBlockingContext")
class HeroDetailsViewModel() : ViewModel() {

    lateinit var heroUiModel: HeroUiModel

    suspend fun getBitmap() : Bitmap =
         withContext(Dispatchers.IO) {
             try {
                 Glide.with(App.instance)
                     .load(heroUiModel.picture)
                     .placeholder(R.drawable.ic_launcher_background)
                     .error(R.drawable.noob_noob)
                     .submit().get().toBitmap()
             }
             catch (e: Exception) {
                 BitmapFactory.decodeResource(
                     App.instance.resources,
                     R.drawable.noob_noob)
             }
        }

}