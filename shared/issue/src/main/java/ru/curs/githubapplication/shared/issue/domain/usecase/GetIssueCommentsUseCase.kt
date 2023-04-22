package ru.curs.githubapplication.shared.issue.domain.usecase

import ru.curs.githubapplication.shared.issue.domain.entity.IssueComment
import ru.curs.githubapplication.shared.issue.domain.repository.IssueRepository

class GetIssueCommentsUseCase(
	private val repository: IssueRepository,
) {

	suspend operator fun invoke(owner: String, repo: String, issueNumber: Int): List<IssueComment> {
		return repository.getIssueComment(owner, repo, issueNumber)
	}
}