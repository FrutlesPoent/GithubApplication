package ru.curs.githubapplication.data.auth.domain.usecase

import net.openid.appauth.AuthorizationService
import net.openid.appauth.TokenRequest
import ru.curs.githubapplication.data.auth.domain.AuthRepository

class PerformTokenRequestUseCase(
	private val repository: AuthRepository,
) {

	suspend operator fun invoke(authService: AuthorizationService, tokenRequest: TokenRequest) {
		repository.performTokenRequest(authService, tokenRequest)
	}
}