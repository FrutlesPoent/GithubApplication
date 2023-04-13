package ru.curs.githubapplication.data.repository

import ru.curs.githubapplication.data.datasource.RepositoryDataSource
import ru.curs.githubapplication.domain.entity.RepositoryEntity
import ru.curs.githubapplication.domain.repository.RepositoryRepository

class RepositoryRepositoryImpl(
	private val dataSource: RepositoryDataSource,
) : RepositoryRepository {

	override suspend fun getRepositoryList(): List<RepositoryEntity> {
		return dataSource.getRepositoryList()
	}

	override suspend fun getRepository(username: String, repo: String): RepositoryEntity {
		return dataSource.getRepository(username, repo)
	}

}