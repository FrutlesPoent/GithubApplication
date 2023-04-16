package ru.curs.githubapplication.data.repository

import ru.curs.githubapplication.data.datasource.RepositoryDataSource
import ru.curs.githubapplication.domain.entity.BranchEntity
import ru.curs.githubapplication.domain.entity.IssuesEntity
import ru.curs.githubapplication.domain.entity.RepositoryContent
import ru.curs.githubapplication.domain.entity.RepositoryEntity
import ru.curs.githubapplication.domain.repository.RepositoryRepository

class RepositoryRepositoryImpl(
	private val dataSource: RepositoryDataSource,
) : RepositoryRepository {

	override suspend fun getRepositoryList(): List<RepositoryEntity> {
		return dataSource.getRepositoryList()
	}

	override suspend fun getRepository(username: String, repo: String): List<RepositoryContent> {
		return dataSource.getRepository(username, repo)
	}

	override suspend fun getRepositoryReadme(username: String): List<RepositoryContent> {
		return dataSource.getRepositoryReadme(username)
	}

	override suspend fun getRepositoryContent(owner: String, repo: String, path: String): List<RepositoryContent> {
		return dataSource.getRepositoryContent(owner, repo, path)
	}

	override suspend fun getRepositoryBranches(owner: String, repo: String): List<BranchEntity> {
		return dataSource.getRepositoryBranches(owner, repo)
	}

	override suspend fun getRepositoryIssues(owner: String, repo: String): List<IssuesEntity> {
		return dataSource.getRepositoryIssues(owner, repo)
	}
}