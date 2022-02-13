package com.example.heros.adapters

import android.widget.ImageView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.example.heros.helpers.NetworkHelper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BindingAdaptersKtTest {

    @Before
    fun init() {
        val networkHelper = NetworkHelper(ApplicationProvider.getApplicationContext())
        if(!networkHelper.isNetworkAvailable())
            throw Exception("Enable Internet connection to perform this test")
    }

    @Test
    fun testBindImageFromUrl() {
        val imageView = ImageView(ApplicationProvider.getApplicationContext())

        assertTrue(imageView.drawable == null)

        runBlocking {
            runOnUiThread{
                bindImageFromUrl(
                    imageView,
                    "https://www.google.com/images/branding/googlelogo/2x/googlelogo_light_color_92x30dp.png"
                )
            }
        }
        assertTrue(imageView.drawable != null)
    }

    @Test
    fun testBindImageFromUrlSetNull() {
        val imageView = ImageView(ApplicationProvider.getApplicationContext())

        assertTrue(imageView.drawable == null)

        runBlocking {
            runOnUiThread{
                bindImageFromUrl(
                    imageView,
                    null
                )
            }
        }
        assertTrue(imageView.drawable == null)
    }

    @Test
    fun testBindImageFromUrlSetEmpty() {
        val imageView = ImageView(ApplicationProvider.getApplicationContext())

        assertTrue(imageView.drawable == null)

        runBlocking {
            runOnUiThread{
                bindImageFromUrl(
                    imageView,
                    ""
                )
            }
        }
        assertTrue(imageView.drawable != null) // get default image
    }

    @Test
    fun testBindImageFromUrlSetWrongUrl() {
        val imageView = ImageView(ApplicationProvider.getApplicationContext())

        assertTrue(imageView.drawable == null)

        runBlocking {
            runOnUiThread{
                bindImageFromUrl(
                    imageView,
                    "https://www.google.com/"
                )
            }
        }
        assertTrue(imageView.drawable != null) // get default image
    }

    @Test
    fun testBindImageFromUrlSetInvalidUrl() {
        val imageView = ImageView(ApplicationProvider.getApplicationContext())

        assertTrue(imageView.drawable == null)

        runBlocking {
            runOnUiThread{
                bindImageFromUrl(
                    imageView,
                    "1111"
                )
            }
        }
        assertTrue(imageView.drawable != null) // get default image
    }
}