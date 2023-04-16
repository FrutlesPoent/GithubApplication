package ru.curs.githubapplication.repository.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.curs.githubapplication.component.ui.mvvm.BaseViewModel
import ru.curs.githubapplication.domain.entity.RepositoryTree
import ru.curs.githubapplication.domain.usecase.GetRepositoryContentUseCase
import ru.curs.githubapplication.domain.usecase.GetRepositoryIssuesUseCase

class RepositoryViewModel(
	private val repository: RepositoryTree,
	private val getRepositoryContentUseCase: GetRepositoryContentUseCase,
	private val getRepositoryIssuesUseCase: GetRepositoryIssuesUseCase,
	private val router: RepositoryRouter,
) : BaseViewModel() {

	var state by mutableStateOf<RepositoryState>(RepositoryState.Initial)
		private set

	fun init() {
		if (state == RepositoryState.Initial)
			launch {
				state = RepositoryState.Loading
				val repositoryContent = async { getRepositoryContentUseCase(repo = repository.repo, owner = repository.owner, path = repository.path) }
				val repositoryIssues = async { getRepositoryIssuesUseCase(repo = repository.repo, owner = repository.owner) }
				state = RepositoryState.Content(repositoryContent.await(), repositoryIssues.await())
			}

	}

	fun openIssues() {
		router.openIssues()
	}

	fun openRepositoryDetail() {
		router.openRepositoryDetail(repository)
	}

	fun back() {
		router.back()
	}
}