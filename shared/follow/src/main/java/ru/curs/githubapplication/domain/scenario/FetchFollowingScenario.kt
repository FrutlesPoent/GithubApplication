package ru.curs.githubapplication.domain.scenario

import ru.curs.githubapplication.domain.entity.Follower
import ru.curs.githubapplication.domain.usecase.GetFollowingUseCase
import ru.curs.githubapplication.domain.usecase.GetUserProfileUseCase

class FetchFollowingScenario(
	private val getFollowingUseCase: GetFollowingUseCase,
	private val getUserProfileUseCase: GetUserProfileUseCase,
) {

	suspend operator fun invoke(): List<Follower> {
		val username = getUserProfileUseCase().login
		return getFollowingUseCase(username)
	}
}