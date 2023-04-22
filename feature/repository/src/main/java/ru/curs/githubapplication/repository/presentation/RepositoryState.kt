package ru.curs.githubapplication.repository.presentation

import ru.curs.githubapplication.domain.entity.BranchEntity
import ru.curs.githubapplication.domain.entity.IssueEntity
import ru.curs.githubapplication.domain.entity.RepositoryContent

sealed class RepositoryState {

	object Initial : RepositoryState()

	object Loading : RepositoryState()

	data class Content(
		val contentList: List<RepositoryContent>,
		val issueList: List<IssueEntity>,
		val branchList: List<BranchEntity>,
		val currentBranch: String,
	) : RepositoryState()
}
