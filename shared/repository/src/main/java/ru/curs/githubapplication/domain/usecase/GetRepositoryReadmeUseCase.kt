package ru.curs.githubapplication.domain.usecase

import ru.curs.githubapplication.domain.entity.RepositoryContent
import ru.curs.githubapplication.domain.repository.RepositoryRepository

class GetRepositoryReadmeUseCase(
	private val repository: RepositoryRepository,
) {

	suspend operator fun invoke(username: String): List<RepositoryContent> {
		return repository.getRepositoryReadme(username)
	}
}