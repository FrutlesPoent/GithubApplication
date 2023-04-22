package ru.curs.githubapplication.shared.issue.domain.usecase

import ru.curs.githubapplication.shared.issue.domain.repository.IssueRepository

class AddCommentUseCase(
	private val repository: IssueRepository,
) {

	suspend operator fun invoke(owner: String, repo: String, issueNumber: Int, body: String) {
		repository.addNewComment(owner, repo, issueNumber, body)
	}
}