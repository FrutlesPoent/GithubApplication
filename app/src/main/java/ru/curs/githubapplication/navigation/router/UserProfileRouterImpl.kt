package ru.curs.githubapplication.navigation.router

import androidx.navigation.NavController
import ru.curs.githubapplication.domain.entity.RepositoryTree
import ru.curs.githubapplication.navigation.NavigationTree
import ru.curs.githubapplication.navigation.ext.toJson
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
		navController.navigate(NavigationTree.Webview.name)
	}

	override fun openRepository(repository: RepositoryTree) {
		val repositoryString = repository.toJson()
		navController.navigate(NavigationTree.Repository.name + "/$repositoryString")
	}
}