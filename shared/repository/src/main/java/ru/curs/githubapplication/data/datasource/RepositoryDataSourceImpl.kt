package ru.curs.githubapplication.data.datasource

import ru.curs.githubapplication.data.api.GithubRepositoryApi
import ru.curs.githubapplication.domain.entity.RepositoryEntity

class RepositoryDataSourceImpl(
	private val api: GithubRepositoryApi,
) : RepositoryDataSource {

	override suspend fun getRepositoryList(): List<RepositoryEntity> =
		api.getRepositoryList()

	override suspend fun getRepository(username: String, repository: String): RepositoryEntity {
		return api.getRepository(owner = username, repo = repository)
	}
}