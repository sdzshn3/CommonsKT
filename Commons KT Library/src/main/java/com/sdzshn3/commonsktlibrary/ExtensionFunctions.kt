package com.sdzshn3.commonsktlibrary

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import java.io.IOException
import java.nio.ByteBuffer
import java.util.*
import kotlin.reflect.KClass

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

/*fun debugPrintLn(any: Any?) {
    if (BuildConfig.DEBUG) {
        println(any)
    }
}*/

fun TextView.underlineText() {
    this.paintFlags = this.paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

/*fun ImageView.loadSvg(url: String) {
    val imageLoader = ImageLoader.Builder(this.context)
        .componentRegistry { add(SvgDecoder(this@loadSvg.context)) }
        .build()

    val request = ImageRequest.Builder(this.context)
        .crossfade(true)
        .crossfade(500)
        .data(url)
        .target(this)
        .build()

    imageLoader.enqueue(request)
}*/

fun uppercaseFirstLetterInEveryWord(string: String?): String? {
    if (string == null) return null
    val strArray = string.split(" ")
    val stringBuilder = StringBuilder()
    for (s in strArray) {
        val cap = s.substring(0, 1).uppercase(Locale.ROOT) + s.substring(1)
        stringBuilder.append("$cap ")
    }
    return stringBuilder.toString()
}


fun Activity.toast(msg: String?) {
    Toast.makeText(this, msg ?: "", Toast.LENGTH_SHORT).show()
}

fun Context.toast(msg: String?) {
    Toast.makeText(this, msg ?: "", Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(msg: String?) {
    Toast.makeText(requireContext(), msg ?: "", Toast.LENGTH_SHORT).show()
}

fun getWeatherIcon(code: String?): String {
    return when (code) {
        "01d", "01n" -> "\uED80"
        "02d", "02n" -> "\uED81"
        "03d", "03n" -> "\uED82"
        "04d", "04n" -> "\uED83"
        "09d", "09n" -> "\uEC69"
        "10d", "10n" -> "\uEC57"
        "11d", "11n" -> "\uEB33"
        "13d", "13n" -> "\uECB9"
        "50d", "50n" -> "\uED1D"
        else -> "\u0020"
    }
}

@Throws(IOException::class)
fun Uri.readBytes(context: Context): ByteArray? =
    context.contentResolver.openInputStream(this)?.buffered()?.use { it.readBytes() }

fun Bitmap.convertToByteArray(): ByteArray {
    //minimum number of bytes that can be used to store this bitmap's pixels
    val size = this.byteCount

    //allocate new instances which will hold bitmap
    val buffer = ByteBuffer.allocate(size)
    val bytes = ByteArray(size)

    //copy the bitmap's pixels into the specified buffer
    this.copyPixelsToBuffer(buffer)

    //rewinds buffer (buffer position is set to zero and the mark is discarded)
    buffer.rewind()

    //transfer bytes from buffer into the given destination array
    buffer.get(bytes)

    //return bitmap's pixels
    return bytes
}

fun Map<String, Any?>.toBundle(): Bundle {
    return bundleOf(*this.toList().toTypedArray())
}

fun Activity.startActivity(kClass: KClass<*>, block: (Intent.() -> Intent)? = null) {
    val intent = Intent(this, kClass.java)
    startActivity(block?.invoke(intent) ?: intent)
}

fun Fragment.startActivity(kClass: KClass<*>, block: (Intent.() -> Intent)? = null) {
    val intent = Intent(requireActivity(), kClass.java)
    startActivity(block?.invoke(intent) ?: intent)
}

/*
fun String.toFormattedDate():String{
    val mdate = this
    val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    df.timeZone = TimeZone.getTimeZone("UTC")
    val date = df.parse(mdate)
    df.timeZone = TimeZone.getDefault()
    val formattedDate = df.format(date)
    val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val parsedDate = df.parse(formattedDate)
    val mformattedDate = outputFormat.format(parsedDate)
    return mformattedDate.toString()

}*/

fun MutableList<*>?.tryRemoveAt(position: Int) {
    if (!this.isNullOrEmpty()) {
        this.removeAt(position)
    }
}