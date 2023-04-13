package ru.curs.githubapplication.data.auth.domain.usecase

import net.openid.appauth.AuthorizationRequest
import ru.curs.githubapplication.data.auth.domain.AuthRepository

class GetAuthRequestUseCase(
	private val repository: AuthRepository,
) : () -> AuthorizationRequest by repository::getAuthRequest
