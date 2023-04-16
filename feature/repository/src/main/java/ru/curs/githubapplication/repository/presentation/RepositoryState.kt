package ru.curs.githubapplication.repository.presentation

import ru.curs.githubapplication.domain.entity.IssuesEntity
import ru.curs.githubapplication.domain.entity.RepositoryContent

sealed class RepositoryState {

	object Initial : RepositoryState()

	object Loading : RepositoryState()

	data class Content(
		val contentList: List<RepositoryContent>,
		val issueList: List<IssuesEntity>,
	) : RepositoryState()
}
