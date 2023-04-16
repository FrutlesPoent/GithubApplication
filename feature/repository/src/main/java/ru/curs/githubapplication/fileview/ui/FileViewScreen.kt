package ru.curs.githubapplication.fileview.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import ru.curs.githubapplication.component.design.LineOfCode
import ru.curs.githubapplication.component.design.navbarcolor.NavbarColor
import ru.curs.githubapplication.component.design.topbar.AppBar
import ru.curs.githubapplication.entity.Line
import ru.curs.githubapplication.fileview.presentation.FileViewState
import ru.curs.githubapplication.fileview.presentation.FileViewViewModel
import ru.curs.githubapplication.repositorydetail.entity.FileUrlTransfer

@Composable
fun FileViewScreen(
	rawDataFile: FileUrlTransfer,
	viewModel: FileViewViewModel = getViewModel { parametersOf(rawDataFile) }
) {
	NavbarColor()
	AnimatedVisibility(visible = viewModel.state == FileViewState.Initial) {
		viewModel.init()
	}
	Surface(
		color = MaterialTheme.colors.primaryVariant,
		modifier = Modifier.fillMaxSize()
	) {
		AnimatedVisibility(visible = viewModel.state is FileViewState.Content) {
			Column {
				val contentState = viewModel.state as FileViewState.Content
				AppBar(text = "Файлы", onBackArrowClick = viewModel::back)
				VerticalRecyclerView(list = contentState.lines)
			}
		}

	}
}

@Composable
fun VerticalRecyclerView(list: List<Line>) {
	Surface(
		color = MaterialTheme.colors.primary,
	) {
		LazyColumn {
			items(items = list) {
				ListItem(position = it.position, line = it.info)
			}
		}
	}
}

@Composable
fun ListItem(position: Int, line: String) {
	Surface(
		color = MaterialTheme.colors.primary,
	) {
		LineOfCode(position = position, line = line)
	}
}