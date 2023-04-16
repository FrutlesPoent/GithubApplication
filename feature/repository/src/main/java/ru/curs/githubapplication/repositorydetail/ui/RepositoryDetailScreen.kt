package ru.curs.githubapplication.repositorydetail.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import ru.curs.githubapplication.component.design.TextWithIcon.TextWithIcon
import ru.curs.githubapplication.component.design.loading.LoadingScreen
import ru.curs.githubapplication.component.design.topbar.AppBar
import ru.curs.githubapplication.domain.entity.RepositoryTree
import ru.curs.githubapplication.repositorydetail.entity.Type
import ru.curs.githubapplication.repositorydetail.entity.TypeOfFile
import ru.curs.githubapplication.repositorydetail.presentation.RepositoryDetailState
import ru.curs.githubapplication.repositorydetail.presentation.RepositoryDetailViewModel

@Composable
fun RepositoryDetailScreen(
	repository: RepositoryTree,
	viewModel: RepositoryDetailViewModel = getViewModel { parametersOf(repository) }
) {
	AnimatedVisibility(visible = viewModel.state == RepositoryDetailState.Initial) {
		AppBar(text = "Файлы", onBackArrowClick = viewModel::appBarBackButtonClicked)
		viewModel.init()
	}

	AnimatedVisibility(visible = viewModel.state == RepositoryDetailState.Loading) {
		LoadingScreen()
	}
	Surface(
		color = MaterialTheme.colors.primaryVariant,
		modifier = Modifier.fillMaxSize(),
	) {
		AnimatedVisibility(visible = viewModel.state is RepositoryDetailState.Content) {

			val contentState = viewModel.state as RepositoryDetailState.Content
			Column {
				AppBar(text = "Файлы", onBackArrowClick = viewModel::appBarBackButtonClicked)
				VerticalRecyclerView(list = contentState.itemList, viewModel::openFileDetail)
			}
		}
	}
}

@Composable
fun VerticalRecyclerView(list: List<TypeOfFile>, button: (text: TypeOfFile) -> Unit) {
	Surface(
		color = MaterialTheme.colors.primary,
	) {
		LazyColumn {
			items(items = list) {
				ListItem(it, button)
			}
		}
	}
}

@Composable
fun ListItem(text: TypeOfFile, openFileClick: (file: TypeOfFile) -> Unit) {
	Surface(
		color = MaterialTheme.colors.primary,
	) {
		TextWithIcon(
			text = text.name,
			rightText = "",
			listClick = { openFileClick(text) },
			painter = when (text.type) {
				Type.FOLDER -> painterResource(id = ru.curs.githubapplication.component.design.resources.R.drawable.folder)
				Type.FILE   -> painterResource(id = ru.curs.githubapplication.component.design.resources.R.drawable.file)
			}
		)
	}
}