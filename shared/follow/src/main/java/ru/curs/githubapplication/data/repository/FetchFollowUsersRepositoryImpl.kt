package ru.curs.githubapplication.data.repository

import ru.curs.githubapplication.data.datasource.FetchFollowUsersDataSource
import ru.curs.githubapplication.domain.entity.Follower
import ru.curs.githubapplication.domain.repository.FetchFollowUsersRepository

class FetchFollowUsersRepositoryImpl(
	private val dataSource: FetchFollowUsersDataSource
) : FetchFollowUsersRepository {

	override suspend fun getFollowers(username: String): List<Follower> =
		dataSource.getFollowers(username)

	override suspend fun getFollowing(username: String): List<Follower> =
		dataSource.getFollowing(username)
}