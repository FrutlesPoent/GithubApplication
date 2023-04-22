package ru.curs.githubapplication.issue.detail.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import ru.curs.githubapplication.component.design.dialog.AddCommentDialog
import ru.curs.githubapplication.component.design.loading.LoadingScreen
import ru.curs.githubapplication.component.design.navbarcolor.NavbarColor
import ru.curs.githubapplication.component.design.topbar.AppBarWithAddNew
import ru.curs.githubapplication.domain.entity.IssueEntity
import ru.curs.githubapplication.issue.detail.presentation.IssueDetailState
import ru.curs.githubapplication.issue.detail.presentation.IssueDetailViewModel
import ru.curs.githubapplication.shared.issue.domain.entity.IssueComment

@Composable
fun IssueDetailScreen(
	issue: IssueEntity,
	viewModel: IssueDetailViewModel = getViewModel { parametersOf(issue) }
) {
	NavbarColor()
	Surface(
		color = MaterialTheme.colors.primaryVariant,
		modifier = Modifier.fillMaxSize()
	) {

	}
	AnimatedVisibility(visible = viewModel.state == IssueDetailState.Initial) {
		viewModel.init()
		LoadingScreen()
	}

	AnimatedVisibility(visible = viewModel.state == IssueDetailState.Loading) {
		LoadingScreen()
	}

	AnimatedVisibility(visible = viewModel.state is IssueDetailState.Content) {
		val content = viewModel.state as IssueDetailState.Content
		val showDialog = remember { mutableStateOf(false) }
		Surface(
			color = MaterialTheme.colors.primary,
		) {
			if (showDialog.value) {
				AddCommentDialog(value = "", setShowDialog = { showDialog.value = it }, setValue = viewModel::addComment)
			}
			Column {
				AppBarWithAddNew(text = "", onBackArrowClick = viewModel::back, onAddClick = { showDialog.value = true })
				Text(text = content.issue.title, style = MaterialTheme.typography.h3, color = Color.White)
				Box(
				) {
					val buttonColors = ButtonDefaults.buttonColors(
						backgroundColor = Color.Green,
						contentColor = Color.White,
					)
					Button(
						onClick = { /*TODO*/ },
						colors = buttonColors,
						shape = RoundedCornerShape(50.dp),
					) {
						Text(text = content.issue.state)
					}
				}
				Divider(thickness = 2.dp, modifier = Modifier.padding(2.dp), color = Color.White)

				Row {
					Image(
						contentScale = ContentScale.Crop,
						painter = rememberAsyncImagePainter(content.issue.user.avatar_url),
						contentDescription = "Image of user",
						modifier = Modifier
							.clip(CircleShape)
							.size(50.dp)
							.padding(8.dp),
					)
					Text(text = content.issue.user.login, style = MaterialTheme.typography.caption)
				}
				Text(
					text = if (content.issue.body.isNullOrBlank()) "Описание не было предоставлено" else content.issue.body!!,
					style = MaterialTheme.typography.body1
				)
				Surface(
					color = MaterialTheme.colors.primaryVariant
				) {
					Spacer(modifier = Modifier.padding(30.dp))
				}
				VerticalRecyclerView(list = content.issueComments)
			}
		}

	}
}

@Composable
fun VerticalRecyclerView(list: List<IssueComment>) {
	Surface(
		color = MaterialTheme.colors.primary,
	) {
		LazyColumn {
			items(items = list) {
				ListItem(it)
				Spacer(modifier = Modifier.padding(5.dp))
			}
		}
	}
}

@Composable
fun ListItem(text: IssueComment) {
	Surface(
		color = MaterialTheme.colors.primary,
	) {
		Column {
			Row {
				Image(
					contentScale = ContentScale.Crop,
					painter = rememberAsyncImagePainter(text.user.avatar_url),
					contentDescription = "Image of user",
					modifier = Modifier
						.clip(CircleShape)
						.size(50.dp)
						.padding(8.dp),
				)
				Text(text = text.user.login, style = MaterialTheme.typography.caption)
			}
			Text(
				text = text.body,
				style = MaterialTheme.typography.body1
			)
		}
	}
}

private fun not() {}