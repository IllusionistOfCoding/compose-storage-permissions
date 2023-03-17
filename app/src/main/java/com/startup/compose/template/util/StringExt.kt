package com.startup.compose.template.util


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.util.regex.Matcher
import java.util.regex.Pattern


@Suppress("unused")
typealias Uuid = String

fun String.isValidEmail(): Boolean {
    val regExpn =
        ("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    val inputStr: CharSequence = toString()
    val pattern: Pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE)
    val matcher: Matcher = pattern.matcher(inputStr)
    return matcher.matches()
}

fun String.isValidPassword(): Boolean {
    val str = this
    var valid = true

    // Password policy check
    // Password should be minimum minimum 8 characters long
    if (str.length < 8) {
        valid = false
    }

    // Password should contain at least one number
    var exp = ".*[0-9].*"
    var pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
    var matcher = pattern.matcher(str)
    if (!matcher.matches()) {
        valid = false
    }

    // Password should contain at least one capital letter
    exp = ".*[A-Z].*"
    pattern = Pattern.compile(exp)
    matcher = pattern.matcher(str)
    if (!matcher.matches()) {
        valid = false
    }

    // Password should contain at least one small letter
    exp = ".*[a-z].*"
    pattern = Pattern.compile(exp)
    matcher = pattern.matcher(str)
    if (!matcher.matches()) {
        valid = false
    }

    // Password should contain at least one special character
    // Allowed special characters : "~!@#$%^&*()-_=+|/,."';:{}[]<>?"
    exp = ".*[~!@#\$%\\^&*()\\-_=+\\|\\[{\\]};:'\",<.>/?].*"
    pattern = Pattern.compile(exp)
    matcher = pattern.matcher(str)
    if (!matcher.matches()) {
        valid = false
    }

    return valid
}


fun String?.fromHex(): ByteArray {
    if (this == null || this == "") return ByteArray(0)
    val len = this.length
    val data = ByteArray(len / 2)
    var i = 0
    while (i < len) {
        data[i / 2] =
            ((Character.digit(this[i], 16) shl 4) + Character.digit(this[i + 1], 16)).toByte()
        i += 2
    }
    return data
}

@Suppress("unused")
fun String?.letNotEmpty(block: (String) -> Unit) {
    this?.let {
        if (isNotEmpty()) {
            block(this)
        }
    }
}

fun String.camelToDash(): String = camelToSeparator('-')


fun String.camelToSnake(): String = camelToSeparator('_')


fun String.camelToSeparator(separator: Char): String {
    var result = ""
    val c = this[0]
    result += c.lowercaseChar()
    for (i in 1 until this.length) {
        val ch = this[i]
        if (Character.isUpperCase(ch)) {
            result += separator
            result = (result
                    + ch.lowercaseChar())
        } else {
            result += ch
        }
    }
    return result
}

fun ByteArray.toHexString(): String {
    return this.joinToString("") {
        java.lang.String.format("%02x", it)
    }
}

fun String.fromBase64(): Bitmap? {
    val base64 = this.split(",").lastOrNull() ?: run {
        return null
    }
    return try {
        val imageBytes = Base64.decode(base64, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(
            imageBytes,
            0,
            imageBytes.size
        )
        decodedImage
    } catch (ex: Exception) {
        ex.printStackTrace()
        null
    }
}


fun String.isValidHashtag(): Boolean = !startsWithDigits()

fun String.onlySpaces(): Boolean {
    var ok = true
    for (c in this) {
        val isSpace = c.isWhitespace()
        if (!isSpace) {
            ok = false
            break
        }
    }
    return ok
}

private fun String.onlyDigitsAndLetters(): Boolean {
    var ok = true
    for (c in this) {
        val isDigit = c.isDigit()
        val isLetter = c.isLetter()
        if (!isDigit && !isLetter) {
            ok = false
            break
        }
    }
    return ok
}

private fun String.startsWithDigits(): Boolean {
    var ok = false
    if (isNotEmpty()) {
        ok = this[0].isDigit()
    }
    return ok
}

fun String.unSpace(): String {
    return replace(" ", "")
}

