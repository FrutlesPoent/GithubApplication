package ru.curs.githubapplication.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.getViewModel
import ru.curs.githubapplication.component.design.loading.LoadingScreen
import ru.curs.githubapplication.component.design.topbar.AppBar
import ru.curs.githubapplication.domain.entity.Follower
import ru.curs.githubapplication.presentation.FollowState
import ru.curs.githubapplication.presentation.FollowViewModel

@Composable
fun FollowScreen(viewModel: FollowViewModel = getViewModel()) {

	AnimatedVisibility(visible = viewModel.state is FollowState.Initial) {
		viewModel.init()
	}

	AnimatedVisibility(visible = viewModel.state is FollowState.Loading) {
		Column {
			AppBar(text = "Следящие", viewModel::back)
			LoadingScreen()
		}

	}

	AnimatedVisibility(visible = viewModel.state is FollowState.Content) {
		ContentScreen(
			followState = viewModel.state as FollowState.Content,
			backButtonClick = viewModel::back,
			openAccountDetailsClick = viewModel::openDetailsFollower
		)
	}
}

@Composable
fun ListItem(userName: String, userImgUrl: String, openAccountDetailsClick: (String) -> Unit) {
	Surface(
		color = MaterialTheme.colors.primary,
		modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
	) {
		Row {
			Image(
				painter = rememberAsyncImagePainter(userImgUrl),
				contentDescription = "user image url",
				contentScale = ContentScale.Crop,
				modifier = Modifier
					.size(100.dp)
					.clip(CircleShape),
			)
			Text(
				text = userName,
				style = MaterialTheme.typography.h4,
				modifier = Modifier
					.fillMaxWidth()
					.clickable { openAccountDetailsClick(userName) },
			)
		}
	}
}

@Composable
fun RecyclerView(usersList: List<Follower>, openAccountDetailsClick: (String) -> Unit) {
	LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
		items(items = usersList) {
			ListItem(userName = it.login, userImgUrl = it.avatar_url, openAccountDetailsClick)
		}
	}
}

@Composable
fun ContentScreen(
	followState: FollowState.Content,
	backButtonClick: () -> Unit,
	openAccountDetailsClick: (String) -> Unit,
) {
	Column {
		AppBar(text = "Следящие", backButtonClick)
		RecyclerView(followState.followersList, openAccountDetailsClick)
	}
}