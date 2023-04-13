package ru.curs.githubapplication.domain.scenario

import ru.curs.githubapplication.domain.entity.Follower
import ru.curs.githubapplication.domain.usecase.GetFollowersUseCase
import ru.curs.githubapplication.domain.usecase.GetUserProfileUseCase

class FetchFollowersScenario(
	private val getUserProfileUseCase: GetUserProfileUseCase,
	private val getFollowersUseCase: GetFollowersUseCase,
) {

	suspend operator fun invoke(): List<Follower> {
		val username = getUserProfileUseCase().login
		println(username)
		return getFollowersUseCase(username)
	}
}