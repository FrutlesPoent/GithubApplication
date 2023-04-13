package ru.curs.githubapplication.domain.repository

import ru.curs.githubapplication.domain.entity.RepositoryEntity

interface RepositoryRepository {

	suspend fun getRepositoryList(): List<RepositoryEntity>

	suspend fun getRepository(username: String, repo: String): RepositoryEntity
}