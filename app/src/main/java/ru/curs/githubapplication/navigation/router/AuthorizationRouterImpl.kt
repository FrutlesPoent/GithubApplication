package ru.curs.githubapplication.navigation.router

import androidx.navigation.NavController
import ru.curs.githubapplication.feature.authorization.presentation.AuthorizationRouter
import ru.curs.githubapplication.navigation.NavigationTree

class AuthorizationRouterImpl(
	private val navController: NavController,
) : AuthorizationRouter {

	override fun openMain() {
		//TODO
	}

	override fun openUserProfile() {
		navController.navigate(NavigationTree.UserProfile.name)
	}
}