package ru.curs.githubapplication.presentation

import ru.curs.githubapplication.domain.entity.Follower

sealed class FollowState {

	object Initial : FollowState()

	object Loading : FollowState()

	data class Content(
		val followersList: List<Follower>
	) : FollowState()

	object Error : FollowState()
}
