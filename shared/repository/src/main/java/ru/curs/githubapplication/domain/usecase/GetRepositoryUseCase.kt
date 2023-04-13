package ru.curs.githubapplication.domain.usecase

import ru.curs.githubapplication.domain.entity.RepositoryEntity
import ru.curs.githubapplication.domain.repository.RepositoryRepository

class GetRepositoryReadmeUseCase(
	private val repository: RepositoryRepository,
) {

	suspend operator fun invoke(username: String): RepositoryEntity {
		return repository.getRepository(username, username)
	}
}