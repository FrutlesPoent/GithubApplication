package ru.curs.githubapplication.issue.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.curs.githubapplication.component.ui.mvvm.BaseViewModel
import ru.curs.githubapplication.domain.entity.IssueEntity
import ru.curs.githubapplication.domain.entity.RepositoryTree
import ru.curs.githubapplication.domain.usecase.GetRepositoryIssuesUseCase
import ru.curs.githubapplication.shared.issue.domain.entity.CreateIssue
import ru.curs.githubapplication.shared.issue.domain.usecase.CreateNewIssueUseCase

class IssueViewModel(
	private val repositoryTree: RepositoryTree,
	private val getRepositoryIssuesUseCase: GetRepositoryIssuesUseCase,
	private val createNewIssueUseCase: CreateNewIssueUseCase,
	private val router: IssueRouter,
) : BaseViewModel() {

	var state by mutableStateOf<IssueState>(IssueState.Initial)
		private set

	fun init() {
		if (state == IssueState.Initial) {
			launch {
				state = IssueState.Loading
				val issueList = getRepositoryIssuesUseCase(repo = repositoryTree.repo, owner = repositoryTree.owner)
				state = IssueState.Content(issueList)
			}
		}
	}

	fun update() {
		launch {
			val issueList = getRepositoryIssuesUseCase(repo = repositoryTree.repo, owner = repositoryTree.owner)
			state = IssueState.Content(issueList)
		}
	}

	fun createNewIssue(title: String, body: String) {
		launch {
			createNewIssueUseCase(
				owner = repositoryTree.owner,
				repo = repositoryTree.repo,
				issue = CreateIssue(title = title, body = body),
			)
		}
		update()
	}

	fun openIssueDetail(issueEntity: IssueEntity) {
		router.openIssueDetail(issueEntity)
	}

	fun back() {
		router.back()
	}
}