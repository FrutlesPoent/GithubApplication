package ru.curs.githubapplication.domain.usecase

import ru.curs.githubapplication.domain.entity.IssuesEntity
import ru.curs.githubapplication.domain.repository.RepositoryRepository

class GetRepositoryIssuesUseCase(
	private val repository: RepositoryRepository,
) {

	suspend operator fun invoke(owner: String, repo: String): List<IssuesEntity> {
		return repository.getRepositoryIssues(owner, repo)
	}
}