package com.chipcerio.newsly.common

import java.util.Date

object PushId {

    private const val PUSH_CHARS = "-0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz"

    fun generate(): String {
        // Modeled after base64 web-safe chars, but ordered by ASCII.

        // Timestamp of last push, used to prevent local collisions if you push twice in one ms.
        val lastPushTime = 0L

        // We generate 72-bits of randomness which get turned into 12 characters and
        // appended to the timestamp to prevent collisions with other clients. We store the last
        // characters we generated because in the event of a collision, we'll use those same
        // characters except "incremented" by one.
        val lastRandChars = CharArray(72)

        var now = Date().time

        val duplicateTime = (now == lastPushTime)

        val timeStampChars = CharArray(8)
        for (i in 7 downTo 0) {
            val module = now % 64
            timeStampChars[i] = PUSH_CHARS[module.toInt()]
            now = Math.floor((now / 64).toDouble()).toLong()
        }

        if (now != 0L) {
            throw AssertionError("We should have converted the entire timestamp.")
        }

        var id = String(timeStampChars)
        if (!duplicateTime) {
            for (i in 0..11) {
                val times = Math.random() * 64
                lastRandChars[i] = Math.floor(times).toChar()
            }
        } else {
            // If the timestamp hasn't changed since last push, use the same random number,
            //except incremented by 1.
            var lastValueOfInt = 0
            var i = 11
            while (i >= 0 && lastRandChars[i].toInt() == 63) {
                lastValueOfInt = i
                lastRandChars[i] = 0.toChar()
                i--
            }
            lastRandChars[lastValueOfInt]++
        }

        for (i in 0..11) {
            id += PUSH_CHARS[lastRandChars[i].toInt()]
        }

        if (id.length != 20) {
            throw AssertionError("Length should be 20.")
        }

        return id
    }
}