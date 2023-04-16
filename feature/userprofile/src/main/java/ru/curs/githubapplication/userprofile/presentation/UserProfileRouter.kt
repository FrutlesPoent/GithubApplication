package ru.curs.githubapplication.userprofile.presentation

import ru.curs.githubapplication.domain.entity.RepositoryTree

interface UserProfileRouter {

	fun openDetails(username: String)

	fun openFollowers()

	fun openFollowing()

	fun openRepository(repositoryName: RepositoryTree)
}