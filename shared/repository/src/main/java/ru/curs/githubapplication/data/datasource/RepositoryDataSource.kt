package ru.curs.githubapplication.data.datasource

import ru.curs.githubapplication.domain.entity.BranchEntity
import ru.curs.githubapplication.domain.entity.IssuesEntity
import ru.curs.githubapplication.domain.entity.RepositoryContent
import ru.curs.githubapplication.domain.entity.RepositoryEntity

interface RepositoryDataSource {

	suspend fun getRepositoryList(): List<RepositoryEntity>

	suspend fun getRepository(username: String, repo: String): List<RepositoryContent>

	suspend fun getRepositoryContent(owner: String, repo: String, path: String): List<RepositoryContent>

	suspend fun getRepositoryReadme(username: String): List<RepositoryContent>

	suspend fun getRepositoryBranches(owner: String, repo: String): List<BranchEntity>

	suspend fun getRepositoryIssues(owner: String, repo: String): List<IssuesEntity>
}