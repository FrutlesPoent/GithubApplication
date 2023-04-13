package ru.curs.githubapplication.domain.usecase

import ru.curs.githubapplication.domain.entity.RepositoryEntity
import ru.curs.githubapplication.domain.repository.RepositoryRepository

class GetRepositoryListUseCase(
	private val repository: RepositoryRepository,
) {

	suspend operator fun invoke(): List<RepositoryEntity> {
		return repository.getRepositoryList()
	}
}