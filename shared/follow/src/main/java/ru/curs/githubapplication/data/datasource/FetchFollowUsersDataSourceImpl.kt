package ru.curs.githubapplication.data.datasource

import ru.curs.githubapplication.data.api.GithubFollowerApi
import ru.curs.githubapplication.domain.entity.Follower

class FetchFollowUsersDataSourceImpl(
	private val api: GithubFollowerApi,
) : FetchFollowUsersDataSource {

	override suspend fun getFollowers(username: String): List<Follower> =
		api.getFollowers(username)

	override suspend fun getFollowing(username: String): List<Follower> =
		api.getFollowing(username)
}