package ru.curs.githubapplication.navigation.router

import androidx.navigation.NavController
import ru.curs.githubapplication.feature.splash.presentation.SplashRouter
import ru.curs.githubapplication.navigation.NavigationTree

class SplashRouterImpl(
	private val navController: NavController,
) : SplashRouter {

	override fun openLogin() {
		navController.popBackStack()
		navController.navigate(NavigationTree.Authorization.name)
	}
}