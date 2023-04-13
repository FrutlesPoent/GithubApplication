package ru.curs.githubapplication.feature.splash.presentation

import ru.curs.githubapplication.component.ui.mvvm.BaseViewModel

class SplashViewModel(
	private val router: SplashRouter,
) : BaseViewModel() {

	init {
		openLogin()
	}

	private fun openLogin() {
		router.openLogin()
	}
}