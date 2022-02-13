package com.example.heroes.adapters

import com.bumptech.glide.load.Key
import java.nio.ByteBuffer
import java.security.MessageDigest
import java.util.*

class OneDayExpirationSignature(private var currentVersion: Int) : Key {

    companion object {
        var versionNumber: Int = Calendar.getInstance().get(Calendar.DAY_OF_YEAR) * 100 +
                Calendar.getInstance().get(Calendar.YEAR)
    }

    override fun equals(other: Any?): Boolean {
        if (other is OneDayExpirationSignature)
            return other == currentVersion
        return false
    }

    override fun hashCode(): Int {
        return currentVersion
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ByteBuffer.allocate(Integer.SIZE).putInt(versionNumber).array())
    }
}