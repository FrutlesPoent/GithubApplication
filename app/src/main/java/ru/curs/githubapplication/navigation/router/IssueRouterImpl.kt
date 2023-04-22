package ru.curs.githubapplication.navigation.router

import androidx.navigation.NavController
import ru.curs.githubapplication.domain.entity.IssueEntity
import ru.curs.githubapplication.issue.presentation.IssueRouter
import ru.curs.githubapplication.navigation.NavigationTree
import ru.curs.githubapplication.navigation.ext.toEncoded
import ru.curs.githubapplication.navigation.ext.toJson

class IssueRouterImpl(
	private val navController: NavController,
) : IssueRouter {

	override fun openIssueDetail(issueEntity: IssueEntity) {
		val encodedUserEntity = issueEntity.user.avatar_url.toEncoded()
		val encodedIssueEntity =
			issueEntity.copy(
				user = issueEntity.user.copy(avatar_url = encodedUserEntity),
				repository_url = issueEntity.repository_url.toEncoded()
			)
		val issue = encodedIssueEntity.toJson()
		navController.navigate(NavigationTree.IssueDetail.name + "/$issue")
	}

	override fun back() {
		navController.popBackStack()
	}

}