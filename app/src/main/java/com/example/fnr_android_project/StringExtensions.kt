package com.example.fnr_android_project

const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$"

fun String?.isFieldValid(regex: Regex): Boolean {
    return this?.contains(regex) ?: run { false }
}