package com.example.heros.ui.heroDetails

import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.heros.App
import com.example.heros.models.HeroUiModel
import com.example.heros.services.IHeroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HeroDetailsViewModel(private val heroService: IHeroService) : ViewModel() {

    lateinit var heroUiModel: HeroUiModel

    suspend fun getBitmap() : Bitmap =
         withContext(Dispatchers.IO) {
           Glide.with(App.instance)
                .load(heroUiModel.picture)
                .submit().get().toBitmap()
        }

}