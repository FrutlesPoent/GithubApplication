package ru.curs.githubapplication.shared.issue.domain.usecase

import ru.curs.githubapplication.shared.issue.domain.entity.IssueComment
import ru.curs.githubapplication.shared.issue.domain.repository.IssueRepository

class GetRepositoryIssueComments(
	private val repository: IssueRepository,
) {

	suspend operator fun invoke(owner: String, repo: String): List<IssueComment> {
		return repository.getRepositoryIssueComments(owner, repo)
	}
}