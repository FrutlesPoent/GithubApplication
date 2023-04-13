package ru.curs.githubapplication.data.auth.data.repository

import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.EndSessionRequest
import net.openid.appauth.TokenRequest
import ru.curs.githubapplication.data.auth.AppAuth
import ru.curs.githubapplication.data.auth.domain.AuthRepository
import ru.curs.githubapplication.data.auth.domain.entity.TokenStorage

class AuthRepositoryImpl : AuthRepository {

	override fun corruptAccessToken() {
		TokenStorage.accessToken = "fake token"
	}

	override fun logout() {
		TokenStorage.accessToken = null
		TokenStorage.refreshToken = null
		TokenStorage.idToken = null
	}

	override fun getAuthRequest(): AuthorizationRequest =
		AppAuth.getAuthRequest()

	override fun getSessionEndRequest(): EndSessionRequest =
		AppAuth.getEndSessionRequest()

	override suspend fun performTokenRequest(authService: AuthorizationService, tokenRequest: TokenRequest) {
		val tokens = AppAuth.performTokenRequestSuspend(authService, tokenRequest)
		//обмен кода на токен произошел успешно, сохраняем токены и завершаем авторизацию
		TokenStorage.accessToken = tokens.accessToken
		TokenStorage.refreshToken = tokens.refreshToken
		TokenStorage.idToken = tokens.idToken
	}
}