package ru.curs.githubapplication.issue.detail.presentation

import ru.curs.githubapplication.domain.entity.IssueEntity
import ru.curs.githubapplication.shared.issue.domain.entity.IssueComment

sealed class IssueDetailState {

	object Initial : IssueDetailState()

	object Loading : IssueDetailState()

	data class Content(
		val issue: IssueEntity,
		val issueComments: List<IssueComment>
	) : IssueDetailState()
}
