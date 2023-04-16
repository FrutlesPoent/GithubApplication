package ru.curs.githubapplication.data.datasource

import ru.curs.githubapplication.data.api.GithubRepositoryApi
import ru.curs.githubapplication.domain.entity.BranchEntity
import ru.curs.githubapplication.domain.entity.IssuesEntity
import ru.curs.githubapplication.domain.entity.RepositoryContent
import ru.curs.githubapplication.domain.entity.RepositoryEntity

class RepositoryDataSourceImpl(
	private val api: GithubRepositoryApi,
) : RepositoryDataSource {

	override suspend fun getRepositoryList(): List<RepositoryEntity> =
		api.getRepositoryList()

	override suspend fun getRepository(username: String, repository: String): List<RepositoryContent> {
		return api.getRepository(owner = username, repo = repository)
	}

	override suspend fun getRepositoryContent(owner: String, repo: String, path: String): List<RepositoryContent> {
		return api.getRepositoryContent(owner = owner, repo = repo, path = path)
	}

	override suspend fun getRepositoryReadme(username: String): List<RepositoryContent> {
		return api.getRepositoryContent(username, username, "")
	}

	override suspend fun getRepositoryBranches(owner: String, repo: String): List<BranchEntity> {
		return api.getBranchesList(owner, repo)
	}

	override suspend fun getRepositoryIssues(owner: String, repo: String): List<IssuesEntity> {
		return api.getIssuesList(owner, repo)
	}
}