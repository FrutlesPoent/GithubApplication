package ru.curs.githubapplication.domain.usecase

import ru.curs.githubapplication.domain.entity.UserProfile
import ru.curs.githubapplication.domain.repository.UserProfileRepository

class GetUserProfileUseCase(
	private val repository: UserProfileRepository,
) {

	suspend operator fun invoke(): UserProfile {
		return repository.getUserProfile()
	}
}