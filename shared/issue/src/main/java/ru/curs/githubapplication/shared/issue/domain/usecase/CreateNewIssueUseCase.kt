package ru.curs.githubapplication.shared.issue.domain.usecase

import ru.curs.githubapplication.shared.issue.domain.entity.CreateIssue
import ru.curs.githubapplication.shared.issue.domain.repository.IssueRepository

class CreateNewIssueUseCase(
	private val repository: IssueRepository,
) {

	suspend operator fun invoke(owner: String, repo: String, issue: CreateIssue) {
		repository.createNewIssue(owner, repo, issue)
	}
}