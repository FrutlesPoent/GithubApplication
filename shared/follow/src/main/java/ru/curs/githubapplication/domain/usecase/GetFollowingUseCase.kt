package ru.curs.githubapplication.domain.usecase

import ru.curs.githubapplication.domain.entity.Follower
import ru.curs.githubapplication.domain.repository.FetchFollowUsersRepository

class GetFollowingUseCase(
	private val repository: FetchFollowUsersRepository,
) {

	suspend operator fun invoke(username: String): List<Follower> =
		repository.getFollowing(username)
}