package com.cesarwillymc.animeapp.util.extension

import com.cesarwillymc.animeapp.util.constants.EMPTY_STRING
import com.cesarwillymc.animeapp.util.constants.ZERO
import com.cesarwillymc.animeapp.util.constants.ZERO_D
import com.cesarwillymc.animeapp.util.constants.ZERO_F

fun Int?.orEmpty(): Int = this ?: ZERO

fun Long?.orEmpty(): Long = this ?: ZERO.toLong()

fun Boolean?.orEmpty(): Boolean = this ?: false

fun Double?.orEmpty(): Double = this ?: ZERO_D

fun Float?.orEmpty(): Float = this ?: ZERO_F

fun String?.orEmpty(): String = this ?: EMPTY_STRING

inline fun <reified T> List<T>?.orEmpty(): List<T> = this ?: listOf<T>()

inline fun <reified T> T?.ifNotNull(crossinline block: (T) -> Unit) {
    if (this != null) {
        block(this)
    }
}
