package ru.curs.githubapplication.domain.repository

import ru.curs.githubapplication.domain.entity.Follower

interface FetchFollowUsersRepository {

	suspend fun getFollowers(username: String): List<Follower>

	suspend fun getFollowing(username: String): List<Follower>
}