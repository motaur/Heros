package com.example.heroes.adapters

import com.bumptech.glide.load.Key
import java.nio.ByteBuffer
import java.security.MessageDigest
import java.util.*

//https://bumptech.github.io/glide/doc/caching.html
//https://tech.fleka.me/how-to-have-an-expired-cache-for-glide-image-library-e69d9b54ef40
class DayExpirationSignature(var currentVersion: Int) : Key {

    companion object {
        //unique id of day
        val versionNumber: Int = Calendar.getInstance().get(Calendar.DAY_OF_YEAR) * 100 +
                Calendar.getInstance().get(Calendar.YEAR)
    }
    //if current version (id of today) not equals to id of day created, then update key and download again
    override fun equals(other: Any?): Boolean {
        if (other is DayExpirationSignature)
            return other.currentVersion == currentVersion
        return false
    }

    override fun hashCode(): Int {
        return currentVersion
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ByteBuffer.allocate(Integer.SIZE).putInt(currentVersion).array())
    }
}