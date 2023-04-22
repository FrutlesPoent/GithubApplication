package ru.curs.githubapplication.navigation.router

import androidx.navigation.NavController
import ru.curs.githubapplication.issue.detail.presentation.IssueDetailRouter

class IssueDetailRouterImpl(
	private val navController: NavController,
) : IssueDetailRouter {

	override fun back() {
		navController.popBackStack()
	}
}