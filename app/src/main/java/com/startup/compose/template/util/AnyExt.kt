package com.startup.compose.template.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.startup.compose.template.BuildConfig

fun Any.logd(message: String){
    if (BuildConfig.DEBUG) {
        Log.d(methodTagLogger() , message)
    }
}

fun Any.loge(message: String, e: Exception? = null){
    if (BuildConfig.DEBUG) {
        Log.e(tagWithMethod() , "error $message")
        e?.printStackTrace()
    }
}

private fun tagWithMethod(): String {
    val stack = Throwable().fillInStackTrace()
    val trace = stack.stackTrace
    return trace[0].className + "." + trace[0].methodName
}

private fun methodTagLogger(): String {
    val callersStackTraceElement = Thread.currentThread().stackTrace[3]
    val tag = callersStackTraceElement.className
    val methodName = callersStackTraceElement.methodName + "()"
    return tag + methodName
}

fun Any.toastDebug(ctx: Context?, msg: String?) {
    if (BuildConfig.DEBUG) {
        Toast.makeText(
            ctx,
            msg ?: "",
            Toast.LENGTH_SHORT
        ).show()
    }
}
/**
 * Return type, we first check if all parameters are not-null values using arguments.all { it != null }.
 * Then, arguments.filterNotNull() converts the nullable vararg parameter (array) into a List of
 * not-null values.
 * example:
       val (first, second, third) = guardLet("Hello", null, Thing("Hello")) { return }
 * **/
inline fun <T: Any> guardLet(vararg elements: T?, block: () -> Nothing): List<T> {
    return if (elements.all { it != null }) {
        elements.filterNotNull()
    } else {
        block()
    }
}

/**
 * Return type, we first check if all parameters are not-null values using arguments.all { it != null }.
 * Then, arguments.filterNotNull() converts the nullable vararg parameter (array) into a List of
 * not-null values.
 * example:
    allLet("Hello", "A", 9, { (first, second, third) ->
            println(first)
            println(second)
            println(third)
        }, { return })
 * **/
inline fun <T: Any> allLet(vararg elements: T?, block: (List<T>) -> Unit, errorBlock: () -> Unit) {
    if (elements.all { it != null }) {
        block(elements.filterNotNull())
    } else {
        errorBlock()
    }
}