package ru.curs.githubapplication.domain.usecase

import ru.curs.githubapplication.domain.entity.IssueEntity
import ru.curs.githubapplication.domain.repository.RepositoryRepository

class GetRepositoryIssuesUseCase(
	private val repository: RepositoryRepository,
) {

	suspend operator fun invoke(owner: String, repo: String): List<IssueEntity> {
		return repository.getRepositoryIssues(owner, repo)
	}
}