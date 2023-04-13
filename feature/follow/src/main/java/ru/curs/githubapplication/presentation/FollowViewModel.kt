package ru.curs.githubapplication.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.curs.githubapplication.component.ui.mvvm.BaseViewModel
import ru.curs.githubapplication.domain.scenario.FetchFollowersScenario
import ru.curs.githubapplication.domain.scenario.FetchFollowingScenario

class FollowViewModel(
	private val fetchFollowersScenario: FetchFollowersScenario,
	private val fetchFollowingScenario: FetchFollowingScenario,
	private val router: FollowRouter,
) : BaseViewModel() {

	var state by mutableStateOf<FollowState>(FollowState.Initial)
		private set

	fun init() {
		if (state == FollowState.Initial) {
			state = FollowState.Loading
			launch {
				val followers = fetchFollowersScenario()
				state = FollowState.Content(followersList = followers)
			}
		}
	}

	fun update() {
		if (state is FollowState.Content) {
			state = FollowState.Loading
			launch {
				val followers = fetchFollowersScenario()
				state = FollowState.Content(followersList = followers)
			}
		}
	}

	fun openDetailsFollower(username: String) {
		router.openDetailsFollower(username)
	}

	fun back() {
		router.back()
	}

}