package ru.curs.githubapplication.userprofile.presentation

import ru.curs.githubapplication.domain.entity.RepositoryContent
import ru.curs.githubapplication.domain.entity.RepositoryEntity
import ru.curs.githubapplication.domain.entity.UserProfile

sealed class UserProfileState {

	object Initial : UserProfileState()

	object Loading : UserProfileState()

	data class Content(
		val userProfile: UserProfile,
		val repositoryList: List<RepositoryEntity>,
		val readmeRepo: List<RepositoryContent>,
	) : UserProfileState()

	object Error : UserProfileState()
}
