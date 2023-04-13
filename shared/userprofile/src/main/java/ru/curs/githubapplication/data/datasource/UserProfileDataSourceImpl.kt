package ru.curs.githubapplication.data.datasource

import ru.curs.githubapplication.data.api.GithubUserApi
import ru.curs.githubapplication.domain.entity.UserProfile

class UserProfileDataSourceImpl(
	private val api: GithubUserApi,
) : UserProfileDataSource {

	override suspend fun getUserProfile(): UserProfile =
		api.getUserProfile()
}