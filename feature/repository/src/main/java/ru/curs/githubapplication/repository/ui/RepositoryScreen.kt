package ru.curs.githubapplication.repository.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import ru.curs.githubapplication.component.design.TextWithIcon.TextWithIcon
import ru.curs.githubapplication.component.design.dialog.ChangeBranchDialog
import ru.curs.githubapplication.component.design.loading.LoadingScreen
import ru.curs.githubapplication.component.design.topbar.AppBar
import ru.curs.githubapplication.domain.entity.BranchEntity
import ru.curs.githubapplication.domain.entity.RepositoryTree
import ru.curs.githubapplication.repository.presentation.RepositoryState
import ru.curs.githubapplication.repository.presentation.RepositoryViewModel

@Composable
fun RepositoryScreen(
	repository: RepositoryTree,
	viewModel: RepositoryViewModel = getViewModel { parametersOf(repository) }
) {

	AnimatedVisibility(visible = viewModel.state == RepositoryState.Initial) {
		LoadingScreen()
		viewModel.init()
	}
	AnimatedVisibility(visible = viewModel.state is RepositoryState.Content) {
		val contentState = viewModel.state as RepositoryState.Content
		Surface(
			color = MaterialTheme.colors.primaryVariant,
			modifier = Modifier
				.fillMaxSize()
		) {
			Column {
				val showDialog = remember { mutableStateOf(false) }
				AppBar(text = "Репозиторий", onBackArrowClick = viewModel::back)
				TextWithIcon(
					text = "Issues",
					rightText = contentState.issueList.size.toString(),
					listClick = { viewModel.openIssues() },
					painter = painterResource(id = ru.curs.githubapplication.component.design.resources.R.drawable.repository),
				)
				TextWithIcon(
					text = "Pull Requests",
					rightText = 0.toString(),
					listClick = { /*TODO*/ },
					painter = painterResource(id = ru.curs.githubapplication.component.design.resources.R.drawable.repository),
				)
				TextWithIcon(
					text = "Contributers",
					rightText = 1.toString(),
					listClick = { /*TODO*/ },
					painter = painterResource(id = ru.curs.githubapplication.component.design.resources.R.drawable.repository),
				)
				Spacer(modifier = Modifier.padding(1.dp))
				TextWithIcon(
					text = contentState.currentBranch,
					rightText = "Поменять ветку",
					listClick = { showDialog.value = true },
					painter = painterResource(id = ru.curs.githubapplication.component.design.resources.R.drawable.repository),
				)
				TextWithIcon(
					text = "Просмотр кода",
					rightText = "",
					listClick = { viewModel.openRepositoryDetail() },
					painter = painterResource(id = ru.curs.githubapplication.component.design.resources.R.drawable.repository)
				)
				if (showDialog.value) {
					ChangeBranchDialog(
						list = contentState.branchList.toListStringExceptCurrent(contentState.currentBranch),
						setShowDialog = { showDialog.value = it },
						setValue = viewModel::openRepositoryWithDifferentBranch
					)
				}
			}
		}
	}
}

private fun List<BranchEntity>.toListStringExceptCurrent(currentBranch: String): List<String> {
	val list = mutableListOf<String>()
	for (it in this) {
		if (it.name != currentBranch)
			list.add(it.name)
	}
	return list
}