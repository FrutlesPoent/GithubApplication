package ru.curs.githubapplication.navigation.router

import androidx.navigation.NavController
import ru.curs.githubapplication.navigation.NavigationTree
import ru.curs.githubapplication.userprofile.presentation.UserProfileRouter

class UserProfileRouterImpl(
	private val navController: NavController,
) : UserProfileRouter {

	override fun openDetails(username: String) {
		navController.navigate(NavigationTree.UserProfileDetail.name + "/$username")
	}

	override fun openFollowers() {
		navController.navigate(NavigationTree.Follows.name)
	}

	override fun openFollowing() {
		// TODO("Not yet implemented")
	}
}