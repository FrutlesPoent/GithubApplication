package ru.curs.githubapplication.navigation.router

import androidx.navigation.NavController
import ru.curs.githubapplication.domain.entity.RepositoryTree
import ru.curs.githubapplication.navigation.NavigationTree
import ru.curs.githubapplication.navigation.ext.toJson
import ru.curs.githubapplication.repositorydetail.entity.FileUrlTransfer
import ru.curs.githubapplication.repositorydetail.presentation.RepositoryDetailRouter
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class RepositoryDetailRouterImpl(
	private val navController: NavController,
) : RepositoryDetailRouter {

	override fun openFile(file: FileUrlTransfer) {
		val rawDataFile = file.toJson()
		val incodeURL = URLEncoder.encode(rawDataFile, StandardCharsets.UTF_8.toString())
		navController.navigate(NavigationTree.FileView.name + "/$incodeURL")
	}

	override fun openFolder(contentList: RepositoryTree) {
		val repository = contentList.toJson()
		val incodeURL = URLEncoder.encode(repository, StandardCharsets.UTF_8.toString())
		navController.navigate(NavigationTree.RepositoryDetail.name + "/$incodeURL")
	}

	override fun back() {
		navController.popBackStack()
	}
}