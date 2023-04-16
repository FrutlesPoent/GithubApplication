package ru.curs.githubapplication.navigation.router

import androidx.navigation.NavController
import ru.curs.githubapplication.fileview.presentation.FileViewRouter

class FileViewRouterImpl(
	private val navController: NavController,
) : FileViewRouter {

	override fun back() {
		navController.popBackStack()
	}

}