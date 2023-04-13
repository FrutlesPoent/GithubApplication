package ru.curs.githubapplication.userprofile.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.curs.githubapplication.component.ui.mvvm.BaseViewModel
import ru.curs.githubapplication.domain.entity.UserProfile
import ru.curs.githubapplication.domain.usecase.GetRepositoryListUseCase
import ru.curs.githubapplication.domain.usecase.GetRepositoryReadmeUseCase
import ru.curs.githubapplication.domain.usecase.GetUserProfileUseCase

class UserProfileViewModel(
	private val getUserProfileUseCase: GetUserProfileUseCase,
	private val getRepositoryListUseCase: GetRepositoryListUseCase,
	private val getRepositoryReadmeUseCase: GetRepositoryReadmeUseCase,
	private val router: UserProfileRouter,
) : BaseViewModel() {

	var state by mutableStateOf<UserProfileState>(UserProfileState.Initial)
		private set

	var userProfileInfo by mutableStateOf<UserProfile?>(null)
		private set

	fun init() {
		if (state == UserProfileState.Initial) {
			launch {
				state = UserProfileState.Loading
				val profileInfo = getUserProfileUseCase()
				val repositoryList = getRepositoryListUseCase()
				val readmeRepository = getRepositoryReadmeUseCase(profileInfo.login)
				userProfileInfo = profileInfo
				state = UserProfileState.Content(
					userProfile = profileInfo,
					repositoryList = repositoryList,
					readmeRepo = readmeRepository,
				)
			}
		}
	}

	fun openFollowers() {
		router.openFollowers()
	}

	fun openRepository(repositoy: String) {
		// todo router.openDetails(repositoy)
	}

	fun nothing() {

	}

	fun openFollowing() {
		router.openFollowing()
	}
}