package ru.curs.githubapplication.userprofile.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.getViewModel
import ru.curs.githubapplication.component.design.TextWithIcon.TextWithIcon
import ru.curs.githubapplication.component.design.error.ErrorScreen
import ru.curs.githubapplication.component.design.icon.IconWithText
import ru.curs.githubapplication.component.design.loading.LoadingProfile
import ru.curs.githubapplication.component.design.navbarcolor.NavbarColor
import ru.curs.githubapplication.component.design.resources.theme.Green100
import ru.curs.githubapplication.component.design.resources.theme.Purple700
import ru.curs.githubapplication.component.design.resources.theme.Yellow80
import ru.curs.githubapplication.component.design.topbar.AppBar
import ru.curs.githubapplication.domain.entity.RepositoryEntity
import ru.curs.githubapplication.userprofile.presentation.UserProfileState
import ru.curs.githubapplication.userprofile.presentation.UserProfileViewModel

@Composable
fun UserProfileScreen(viewModel: UserProfileViewModel = getViewModel()) {
	NavbarColor()

	Surface(
		color = MaterialTheme.colors.primaryVariant,
		modifier = Modifier
			.fillMaxSize()
	) {
		AnimatedVisibility(visible = viewModel.state == UserProfileState.Initial) {
			LoadingProfile()
			viewModel.init()
		}

		AnimatedVisibility(
			visible = viewModel.state == UserProfileState.Loading
		) {
			Column {
				AppBar(text = "Профиль", viewModel::nothing)
				LoadingProfile()
			}

		}

		AnimatedVisibility(visible = viewModel.state == UserProfileState.Error) {
			Column {
				AppBar(text = "Профиль", viewModel::nothing)
				ErrorScreen(viewModel::loadOnError)
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
				Spacer(modifier = Modifier.padding(4.dp))
				HorizontalRecyclerView(
					list = contentState.repositoryList,
					username = contentState.userProfile.login,
					imgUrl = contentState.userProfile.avatar_url,
					openRepository = viewModel::openRepository,
				)
				Spacer(modifier = Modifier.padding(4.dp))
				TextWithIcon(
					text = "Репозитории",
					rightText = (contentState.userProfile.public_repos + contentState.userProfile.owned_private_repos).toString(),
					viewModel::nothing,
					painter = painterResource(ru.curs.githubapplication.component.design.resources.R.drawable.repository)
				)
				Spacer(modifier = Modifier.padding(4.dp))
				TextWithIcon(
					text = "Организации",
					rightText = 1.toString(),
					viewModel::nothing,
					painter = painterResource(id = ru.curs.githubapplication.component.design.resources.R.drawable.organization)
				)
				Spacer(modifier = Modifier.padding(4.dp))
				TextWithIcon(
					text = "Обо мне",
					rightText = if (contentState.readmeRepo.first().download_url != null) 1.toString() else 0.toString(),
					listClick = { /*TODO*/ },
					painter = rememberAsyncImagePainter(contentState.userProfile.avatar_url)
				)
//				if (contentState.readmeRepo.first().download_url != null) {
//					MarkDown(
//						url = URL(contentState.readmeRepo.first().git_url),
//						modifier = Modifier
//							.fillMaxSize(),
//						shouldOpenUrlInBrowser = true,
//					)
//				}
			}
		}
	}
}

@Composable
fun HorizontalRecyclerView(list: List<RepositoryEntity>, username: String, imgUrl: String, openRepository: (String) -> Unit) {
	Surface(color = MaterialTheme.colors.primary) {
		Column {
			Row {
				Image(
					painter = painterResource(id = ru.curs.githubapplication.component.design.resources.R.drawable.repository),
					contentDescription = "RepositoryIcon",
					modifier = Modifier.size(60.dp)
				)
				Text(text = "Репозитории")
			}
			LazyRow {
				items(items = list) {
					val language: String = if (it.language.toString() == "null") {
						"Неизвестный"
					} else {
						it.language.toString()
					}
					ListItem(
						username = username,
						repositoryName = it.name!!,
						language = language,
						imageUrl = imgUrl,
						openRepository = openRepository,
					)
				}
			}
		}
	}
}

@Composable
private fun ListItem(
	username: String,
	repositoryName: String,
	language: String,
	imageUrl: String,
	openRepository: (String) -> Unit,
) {
	Surface(
		color = MaterialTheme.colors.secondary,
		modifier = Modifier
			.padding(vertical = 4.dp, horizontal = 8.dp)
			.fillMaxWidth()
	) {
		Column(modifier = Modifier.clickable { openRepository(repositoryName) }
		) {
			Row(
				modifier = Modifier
					.height(70.dp)
					.width(150.dp)

			) {
				Image(
					painter = rememberAsyncImagePainter(imageUrl),
					contentDescription = "User image",
					modifier = Modifier.size(25.dp),
				)
				Spacer(modifier = Modifier.padding(4.dp))
				Text(text = username, style = MaterialTheme.typography.caption, color = Color.White)
			}
			Spacer(modifier = Modifier.padding(4.dp))
			Text(text = language, style = MaterialTheme.typography.body1, color = language.color())
			Text(text = repositoryName, style = MaterialTheme.typography.body1, color = Color.White)
		}

	}
}

private fun String.color(): Color {
	return when (this) {
		"C"      -> Gray
		"Java"   -> Yellow80
		"Kotlin" -> Purple700
		"C#"     -> Green100

		else     -> {
			Color.White
		}
	}
}
