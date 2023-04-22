package ru.curs.githubapplication.navigation.ext

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun <A> A.toJson(): String? =
	Gson().toJson(this)

fun <A> String.fromJson(type: Class<A>): A =
	Gson().fromJson(this, type)

fun String.toEncoded(): String =
	URLEncoder.encode(this, StandardCharsets.UTF_8.toString())