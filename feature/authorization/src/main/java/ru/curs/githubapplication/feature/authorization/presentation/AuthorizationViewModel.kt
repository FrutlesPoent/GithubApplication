package ru.curs.githubapplication.feature.authorization.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import net.openid.appauth.TokenRequest
import ru.curs.githubapplication.component.ui.mvvm.BaseViewModel
import ru.curs.githubapplication.data.auth.domain.usecase.GetAuthRequestUseCase
import ru.curs.githubapplication.data.auth.domain.usecase.PerformTokenRequestUseCase

class AuthorizationViewModel(
	@SuppressLint("StaticFieldLeak") private val context: Context,
	private val getAuthRequestUseCase: GetAuthRequestUseCase,
	private val performTokenRequestUseCase: PerformTokenRequestUseCase,
	private val router: AuthorizationRouter,
) : BaseViewModel() {

	var state by mutableStateOf<AuthorizationState>(AuthorizationState.Content(isLoginPageOpen = false))
		private set

	private val authService: AuthorizationService = AuthorizationService(context)

	private val openAuthPageEventChannel = Channel<Intent>(Channel.BUFFERED)

	val openAuthPageFlow: Flow<Intent>
		get() = openAuthPageEventChannel.receiveAsFlow()

	fun openLoginPage() {
		val contentState = state as AuthorizationState.Content
		val customTabsIntent = CustomTabsIntent.Builder().build()

		val authRequest = getAuthRequestUseCase()

		val openAuthPageIntent = authService.getAuthorizationRequestIntent(
			authRequest,
			customTabsIntent,
		)
		openAuthPageEventChannel.trySendBlocking(openAuthPageIntent)
		state = contentState.copy(isLoginPageOpen = true)
	}

	fun handleResponseIntent(intent: Intent) {
		val exception = AuthorizationException.fromIntent(intent)
		val tokenExchangeRequest = AuthorizationResponse.fromIntent(intent)?.createTokenExchangeRequest()
		when {
			exception != null            -> authCodeFailed(exception)
			tokenExchangeRequest != null -> authCodeReceived(tokenExchangeRequest)
		}
	}

	private fun authCodeFailed(exception: AuthorizationException) {
		val contentState = state as AuthorizationState.Content

		state = contentState.copy(isLoginPageOpen = false)
	}

	private fun authCodeReceived(tokenRequest: TokenRequest) {
		launch {
			performTokenRequestUseCase(
				authService = authService,
				tokenRequest = tokenRequest,
			)
			router.openUserProfile()
		}
	}
}