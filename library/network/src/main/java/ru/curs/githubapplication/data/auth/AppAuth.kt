package ru.curs.githubapplication.data.auth

import android.net.Uri
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ResponseTypeValues

object AppAuth {

	private object AuthConfig {

		const val AUTH_URI = "https://github.com/login/oauth/authorize"
		const val TOKEN_URI = "https://github.com/login/oauth/access_token"
		const val END_SESSION_URI = "https://github.com/logout"
		const val RESPONSE_TYPE = ResponseTypeValues.CODE
		const val SCOPE = "user,repo"

		const val CLIENT_ID = "310164ec9635184ffc56"
		const val CLIENT_SECRET = "6061b6176d20e5e374a8e336396e62864fcd2a85"
		const val CALLBACK_URL = "ru.kts.oauth://github.com/callback"
		const val LOGOUT_CALLBACK_URL = "ru.kts.oauth://github.com/logout_callback"

	}

	private val serviceConfiguration = AuthorizationServiceConfiguration(
		Uri.parse(AuthConfig.AUTH_URI)
	)


}