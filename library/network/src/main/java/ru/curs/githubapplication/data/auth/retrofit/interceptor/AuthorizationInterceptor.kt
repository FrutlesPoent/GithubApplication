package ru.curs.githubapplication.data.auth.retrofit.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import ru.curs.githubapplication.data.auth.domain.entity.TokenStorage

class AuthorizationInterceptor : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		return chain.request()
			.addTokenHeader()
			.let { chain.proceed(it) }
	}

	private fun Request.addTokenHeader(): Request {
		val authHeaderName = "Authorization"
		return newBuilder()
			.apply {
				val token = TokenStorage.accessToken
				if (token != null) {
					header(authHeaderName, token.withBearer())
				}
			}
			.build()
	}

	private fun String.withBearer() = "Bearer $this"
}