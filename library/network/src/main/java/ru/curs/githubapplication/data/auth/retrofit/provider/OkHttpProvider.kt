package ru.curs.githubapplication.data.auth.retrofit.provider

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

private const val TIMEOUT: Long = 60

fun provideOkHttpClient(
	interceptors: List<Interceptor> = emptyList(),
): OkHttpClient =
	okHttpClientSetups(
		interceptors = interceptors,
	)

internal fun okHttpClientSetups(
	interceptors: List<Interceptor>
): OkHttpClient =
	OkHttpClient().newBuilder()
		.apply {
			connectTimeout(TIMEOUT, TimeUnit.SECONDS)
			writeTimeout(TIMEOUT, TimeUnit.SECONDS)
			readTimeout(TIMEOUT, TimeUnit.SECONDS)
			interceptors.forEach { addInterceptor(it) }
		}
		.build()