package ru.curs.githubapplication.data.datasource

import ru.curs.githubapplication.domain.entity.RepositoryEntity

interface RepositoryDataSource {

	suspend fun getRepositoryList(): List<RepositoryEntity>

	suspend fun getRepository(username: String, repo: String): RepositoryEntity
}