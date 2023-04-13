package ru.curs.githubapplication.userprofile.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.curs.githubapplication.component.ui.mvvm.BaseViewModel
import ru.curs.githubapplication.domain.entity.UserProfile
import ru.curs.githubapplication.domain.usecase.GetUserProfileUseCase

class UserProfileViewModel(
	private val getUserProfileUseCase: GetUserProfileUseCase,
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
				userProfileInfo = profileInfo
				state = UserProfileState.Content(userProfile = profileInfo)
			}
		}
	}

	fun openFollowers() {
		router.openFollowers()
	}

	fun openFollowing() {
		router.openFollowing()
	}
}