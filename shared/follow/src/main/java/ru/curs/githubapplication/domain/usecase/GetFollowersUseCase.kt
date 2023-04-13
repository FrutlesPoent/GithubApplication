package ru.curs.githubapplication.domain.usecase

import ru.curs.githubapplication.domain.entity.Follower
import ru.curs.githubapplication.domain.repository.FetchFollowUsersRepository

class GetFollowersUseCase(
	private val repository: FetchFollowUsersRepository,
) {

	suspend operator fun invoke(username: String): List<Follower> =
		repository.getFollowers(username)
}