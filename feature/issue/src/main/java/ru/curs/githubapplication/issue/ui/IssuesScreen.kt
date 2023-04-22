package ru.curs.githubapplication.issue.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import ru.curs.githubapplication.component.design.TextWithIcon.TextWithIcon
import ru.curs.githubapplication.component.design.dialog.AddDialog
import ru.curs.githubapplication.component.design.navbarcolor.NavbarColor
import ru.curs.githubapplication.component.design.topbar.AppBarWithAddNew
import ru.curs.githubapplication.domain.entity.IssueEntity
import ru.curs.githubapplication.domain.entity.RepositoryTree
import ru.curs.githubapplication.issue.presentation.IssueState
import ru.curs.githubapplication.issue.presentation.IssueViewModel

@Composable
fun IssueScreen(
	repository: RepositoryTree,
	viewModel: IssueViewModel = getViewModel { parametersOf(repository) }
) {
	NavbarColor()
	AnimatedVisibility(visible = viewModel.state == IssueState.Initial) {
		viewModel.init()
	}
	Surface(
		color = MaterialTheme.colors.primaryVariant,
		modifier = Modifier.fillMaxSize()
	) {

		AnimatedVisibility(visible = viewModel.state is IssueState.Content) {
			val content = viewModel.state as IssueState.Content
			val showDialog = remember { mutableStateOf(false) }
			Column {
				AppBarWithAddNew(text = "Issues", onBackArrowClick = { viewModel.back() }, onAddClick = { showDialog.value = true })
				if (showDialog.value) {
					AddDialog(value = "", setShowDialog = { showDialog.value = it }, setValue = viewModel::createNewIssue)
				}
				if (content.issueList.isNotEmpty()) {
					VerticalRecyclerView(itemList = content.issueList, viewModel::openIssueDetail)
				} else {
					Box(contentAlignment = Alignment.Center) {
						Text(
							text = "На данный момент нет открытых проблем для данного проекта",
							style = MaterialTheme.typography.body1,
							color = Color.White,
						)
					}
				}
			}
		}
	}
}

@Composable
fun VerticalRecyclerView(itemList: List<IssueEntity>, openDetail: (issue: IssueEntity) -> Unit) {
	Surface(
		color = MaterialTheme.colors.primary,
	) {
		LazyColumn {
			items(items = itemList) {
				ListItem(item = it, openDetail)
			}
		}
	}
}

@Composable
fun ListItem(item: IssueEntity, openDetail: (issue: IssueEntity) -> Unit) {
	Surface(color = MaterialTheme.colors.primary) {
		TextWithIcon(
			text = item.title,
			rightText = "",
			listClick = { openDetail(item) },
			painter = painterResource(id = ru.curs.githubapplication.component.design.resources.R.drawable.issue)
		)
	}
}

