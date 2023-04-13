package ru.curs.githubapplication.userprofile.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.getViewModel
import ru.curs.githubapplication.component.design.icon.IconWithText
import ru.curs.githubapplication.component.design.loading.LoadingProfile
import ru.curs.githubapplication.component.design.topbar.AppBar
import ru.curs.githubapplication.userprofile.presentation.UserProfileState
import ru.curs.githubapplication.userprofile.presentation.UserProfileViewModel

@Composable
fun UserProfileScreen(viewModel: UserProfileViewModel = getViewModel()) {

	AnimatedVisibility(visible = viewModel.state == UserProfileState.Initial) {
		viewModel.init()
	}

	AnimatedVisibility(
		visible = viewModel.state == UserProfileState.Loading
	) {
		Column {
			AppBar(text = "Профиль", viewModel::init)
			LoadingProfile()
		}

	}
	AnimatedVisibility(visible = viewModel.state is UserProfileState.Content) {
		val contentState = viewModel.state as UserProfileState.Content
		Column {
			AppBar("Профиль", viewModel::init)
			IconWithText(
				text = contentState.userProfile.name,
				text2 = contentState.userProfile.login,
				imgUrl = contentState.userProfile.avatar_url,
				followersCount = contentState.userProfile.followers,
				followingCount = contentState.userProfile.following,
				onFollowersClick = viewModel::openFollowers,
				onFollowingClick = viewModel::openFollowing,
			)
		}
	}
}
