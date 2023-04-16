package ru.curs.githubapplication.repository.presentation

import ru.curs.githubapplication.domain.entity.RepositoryTree

interface RepositoryRouter {

	fun openRepositoryDetail(contentList: RepositoryTree)

	fun openIssues()

	fun back()
}