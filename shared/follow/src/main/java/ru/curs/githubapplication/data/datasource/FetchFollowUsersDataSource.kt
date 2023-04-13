package ru.curs.githubapplication.data.datasource

import ru.curs.githubapplication.domain.entity.Follower

interface FetchFollowUsersDataSource {

	suspend fun getFollowers(username: String): List<Follower>

	suspend fun getFollowing(username: String): List<Follower>
}