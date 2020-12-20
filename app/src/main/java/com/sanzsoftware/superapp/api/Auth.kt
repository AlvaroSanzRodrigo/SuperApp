package com.sanzsoftware.superapp.api

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class Auth{
    companion object {
        const val PUBLIC_KEY = "eb06c3a6f6a4ff6bb5f7cf3ae43cf77d"
        private const val PRIVATE_KEY = "26a063f9a8a75fa1e6a7c7f3486af75e2308030c"

        fun getHash(timestamp: String): String {
            return this.md5(timestamp + PRIVATE_KEY + PUBLIC_KEY)!!
        }
        private fun md5(code: String): String? {
            try {
                // Create MD5 Hash
                val digest = MessageDigest
                    .getInstance("MD5")
                digest.update(code.toByteArray())
                val messageDigest = digest.digest()

                // Create Hex String
                val hexString = StringBuilder()
                for (aMessageDigest in messageDigest) {
                    var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                    while (h.length < 2) h = "0$h"
                    hexString.append(h)
                }
                return hexString.toString()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }
            return ""
        }
    }
}