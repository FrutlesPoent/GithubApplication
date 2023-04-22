package ru.curs.githubapplication.shared.issue.domain.repository

import ru.curs.githubapplication.shared.issue.domain.entity.CreateIssue
import ru.curs.githubapplication.shared.issue.domain.entity.IssueComment

interface IssueRepository {

	suspend fun getRepositoryIssueComments(owner: String, repo: String): List<IssueComment>

	suspend fun getIssueComment(owner: String, repo: String, issueNumber: Int): List<IssueComment>

	suspend fun createNewIssue(owner: String, repo: String, issue: CreateIssue)

	suspend fun addNewComment(owner: String, repo: String, issueNumber: Int, body: String)
}