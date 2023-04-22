package ru.curs.githubapplication.issue.presentation

import ru.curs.githubapplication.domain.entity.IssueEntity

sealed class IssueState {

	object Initial : IssueState()

	object Loading : IssueState()

	data class Content(
		val issueList: List<IssueEntity>
	) : IssueState()
}
