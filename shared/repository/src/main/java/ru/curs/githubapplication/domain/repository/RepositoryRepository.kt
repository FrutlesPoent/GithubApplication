package ru.curs.githubapplication.domain.repository

import ru.curs.githubapplication.domain.entity.BranchEntity
import ru.curs.githubapplication.domain.entity.IssueEntity
import ru.curs.githubapplication.domain.entity.RepositoryContent
import ru.curs.githubapplication.domain.entity.RepositoryEntity

interface RepositoryRepository {

	suspend fun getRepositoryList(): List<RepositoryEntity>

	suspend fun getRepository(username: String, repo: String): List<RepositoryContent>

	suspend fun getRepositoryReadme(username: String): List<RepositoryContent>

	suspend fun getRepositoryContent(owner: String, repo: String, path: String, branch: String?): List<RepositoryContent>

	suspend fun getRepositoryBranches(owner: String, repo: String): List<BranchEntity>

	suspend fun getRepositoryIssues(owner: String, repo: String): List<IssueEntity>
}