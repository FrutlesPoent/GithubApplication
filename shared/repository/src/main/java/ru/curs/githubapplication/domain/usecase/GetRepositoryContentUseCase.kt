package ru.curs.githubapplication.domain.usecase

import ru.curs.githubapplication.domain.entity.RepositoryContent
import ru.curs.githubapplication.domain.repository.RepositoryRepository

class GetRepositoryContentUseCase(
	private val repository: RepositoryRepository,
) {

	suspend operator fun invoke(owner: String, repo: String, path: String, branch: String?): List<RepositoryContent> {
		return repository.getRepositoryContent(owner, repo, path, branch)
	}
}