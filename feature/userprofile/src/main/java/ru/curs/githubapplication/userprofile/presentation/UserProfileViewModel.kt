package ru.curs.githubapplication.userprofile.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.curs.githubapplication.component.ui.mvvm.BaseViewModel
import ru.curs.githubapplication.component.ui.mvvm.async
import ru.curs.githubapplication.domain.entity.RepositoryTree
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
			load()
		}
	}

	private fun load() {
		launch {
			try {
				state = UserProfileState.Loading
				val profileInfoDeffered = async { getUserProfileUseCase() }
				val repositoryList = async { getRepositoryListUseCase() }

				val profileInfo = profileInfoDeffered.await()
				val readmeRepository = getRepositoryReadmeUseCase(profileInfo.login)
				userProfileInfo = profileInfo
				state = UserProfileState.Content(
					userProfile = profileInfo,
					repositoryList = repositoryList.await(),
					readmeRepo = readmeRepository,
				)
			} catch (throwable: Exception) {
				state = UserProfileState.Error
				throwable.printStackTrace()
			}
		}
	}

	fun loadOnError() {
		if (state == UserProfileState.Error) {
			load()
		}
	}

	fun openFollowers() {
		router.openFollowers()
	}

	fun openRepository(repository: String) {
		val contentState = state as UserProfileState.Content
		val repositoryInfo = RepositoryTree(
			repo = repository,
			owner = contentState.userProfile.login,
		)
		router.openRepository(repositoryInfo)
	}

	fun nothing() {

	}

	fun openFollowing() {
		router.openFollowing()
	}
}