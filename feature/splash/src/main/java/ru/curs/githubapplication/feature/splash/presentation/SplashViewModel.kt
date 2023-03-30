package ru.curs.githubapplication.feature.splash.presentation

import kotlinx.coroutines.delay
import ru.curs.githubapplication.component.ui.mvvm.BaseViewModel

class SplashViewModel(
	private val router: SplashRouter,
) : BaseViewModel() {

	init {
		launch {
			openLogin()
		}
	}

	private fun openLogin() {
		router.openLogin()
	}
}