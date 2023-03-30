package ru.curs.githubapplication.feature.authorization.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ru.curs.githubapplication.component.ui.mvvm.BaseViewModel
import java.lang.Thread.sleep

class AuthorizationViewModel(
	private val router: AuthorizationRouter,
) : BaseViewModel() {

	var state by mutableStateOf<AuthorizationState>(AuthorizationState.Initial)
		private set

	init {
		state = AuthorizationState.Loading
		sleep(1000)
		state = AuthorizationState.Content

	}

	fun login(email: String, password: String) {

		router.openMain()
	}

	fun nothing() {
		return
	}
}