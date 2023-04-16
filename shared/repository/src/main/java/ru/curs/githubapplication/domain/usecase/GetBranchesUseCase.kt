package ru.curs.githubapplication.domain.usecase

import ru.curs.githubapplication.domain.entity.BranchEntity
import ru.curs.githubapplication.domain.repository.RepositoryRepository

class GetBranchesUseCase(
	private val repository: RepositoryRepository,
) {

	suspend operator fun invoke(owner: String, repo: String): List<BranchEntity> {
		return repository.getRepositoryBranches(owner, repo)
	}
}