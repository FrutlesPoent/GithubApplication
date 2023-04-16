package ru.curs.githubapplication.navigation.router

import androidx.navigation.NavController
import ru.curs.githubapplication.domain.entity.RepositoryContent
import ru.curs.githubapplication.domain.entity.RepositoryTree
import ru.curs.githubapplication.navigation.NavigationTree
import ru.curs.githubapplication.navigation.ext.toJson
import ru.curs.githubapplication.repository.presentation.RepositoryRouter

class RepositoryRouterImpl(
	private val navController: NavController,
) : RepositoryRouter {

	override fun openRepositoryDetail(contentList: RepositoryTree) {
		val repository = contentList.toJson()
		navController.navigate(NavigationTree.RepositoryDetail.name + "/$repository")
	}

	override fun back() {
		navController.popBackStack()
	}
}