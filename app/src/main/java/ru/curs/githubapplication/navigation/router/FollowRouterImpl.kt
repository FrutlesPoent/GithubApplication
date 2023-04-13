package ru.curs.githubapplication.navigation.router

import androidx.navigation.NavController
import ru.curs.githubapplication.presentation.FollowRouter

class FollowRouterImpl(
	private val navController: NavController,
) : FollowRouter {

	override fun back() {
		navController.popBackStack()
	}

	override fun openDetailsFollower(username: String) {
		TODO("Not yet implemented")
	}
}