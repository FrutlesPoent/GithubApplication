package ru.curs.githubapplication.data.auth

import android.net.Uri
import androidx.core.net.toUri
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ClientAuthentication
import net.openid.appauth.ClientSecretPost
import net.openid.appauth.EndSessionRequest
import net.openid.appauth.GrantTypeValues
import net.openid.appauth.ResponseTypeValues
import net.openid.appauth.TokenRequest
import ru.curs.githubapplication.data.auth.domain.entity.Tokens
import kotlin.coroutines.suspendCoroutine

object AppAuth {

	private val serviceConfiguration = AuthorizationServiceConfiguration(
		Uri.parse(AuthConfig.AUTH_URI),
		Uri.parse(AuthConfig.TOKEN_URI),
		null, // registration endpoint
		Uri.parse(AuthConfig.END_SESSION_URI)
	)

	fun getAuthRequest(): AuthorizationRequest {
		val redirectUri = AuthConfig.CALLBACK_URL.toUri()

		return AuthorizationRequest.Builder(
			serviceConfiguration,
			AuthConfig.CLIENT_ID,
			AuthConfig.RESPONSE_TYPE,
			redirectUri
		)
			.setScope(AuthConfig.SCOPE)
			.build()
	}

	fun getEndSessionRequest(): EndSessionRequest =
		EndSessionRequest.Builder(serviceConfiguration)
			.setPostLogoutRedirectUri(AuthConfig.LOGOUT_CALLBACK_URL.toUri())
			.build()

	fun getRefreshTokenRequest(refreshToken: String): TokenRequest {
		return TokenRequest.Builder(
			serviceConfiguration,
			AuthConfig.CLIENT_ID
		)
			.setGrantType(GrantTypeValues.REFRESH_TOKEN)
			.setScopes(AuthConfig.SCOPE)
			.setRefreshToken(refreshToken)
			.build()
	}

	suspend fun performTokenRequestSuspend(
		authService: AuthorizationService,
		tokenRequest: TokenRequest,
	): Tokens {
		return suspendCoroutine { continuation ->
			authService.performTokenRequest(tokenRequest, getClientAuthentication()) { response, ex ->
				when {
					response != null -> {
						//получение токена произошло успешно
						val tokens = Tokens(
							accessToken = response.accessToken.orEmpty(),
							refreshToken = response.refreshToken.orEmpty(),
							idToken = response.idToken.orEmpty()
						)
						continuation.resumeWith(Result.success(tokens))
					}
					//получение токенов произошло неуспешно, показываем ошибку
					ex != null       -> {
						continuation.resumeWith(Result.failure(ex))
					}

					else             -> error("unreachable")
				}
			}
		}
	}

	private fun getClientAuthentication(): ClientAuthentication =
		ClientSecretPost(AuthConfig.CLIENT_SECRET)

	private object AuthConfig {

		const val AUTH_URI = "https://github.com/login/oauth/authorize"
		const val TOKEN_URI = "https://github.com/login/oauth/access_token"
		const val END_SESSION_URI = "https://github.com/logout"
		const val RESPONSE_TYPE = ResponseTypeValues.CODE
		const val SCOPE = "user,repo"

		const val CLIENT_ID = "351c4feea007acedacac"
		const val CLIENT_SECRET = "34c32b98ba03d07f6c228fd4c0e7246cbf62ce97"
		const val CALLBACK_URL = "ru.curs.githubapplication://github.com/callback"
		const val LOGOUT_CALLBACK_URL = "ru.curs.githubapplication://github.com/logout_callback"
	}
}