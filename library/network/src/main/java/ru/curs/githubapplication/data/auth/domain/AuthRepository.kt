package ru.curs.githubapplication.data.auth.domain

import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.EndSessionRequest
import net.openid.appauth.TokenRequest

interface AuthRepository {

	fun corruptAccessToken()

	fun logout()

	fun getAuthRequest(): AuthorizationRequest

	fun getSessionEndRequest(): EndSessionRequest

	suspend fun performTokenRequest(
		authService: AuthorizationService,
		tokenRequest: TokenRequest,
	)

}