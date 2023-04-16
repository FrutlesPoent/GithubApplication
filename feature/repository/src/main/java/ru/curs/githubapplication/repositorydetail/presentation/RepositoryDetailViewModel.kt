package ru.curs.githubapplication.repositorydetail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.curs.githubapplication.component.ui.mvvm.BaseViewModel
import ru.curs.githubapplication.domain.entity.RepositoryContent
import ru.curs.githubapplication.domain.entity.RepositoryTree
import ru.curs.githubapplication.domain.usecase.GetRepositoryContentUseCase
import ru.curs.githubapplication.parser.ParseFileNameUseCase
import ru.curs.githubapplication.parser.ParseFolderNameUseCase
import ru.curs.githubapplication.repositorydetail.entity.FileUrlTransfer
import ru.curs.githubapplication.repositorydetail.entity.Type
import ru.curs.githubapplication.repositorydetail.entity.TypeOfFile
import java.net.URL

class RepositoryDetailViewModel(
	private val repository: RepositoryTree,
	private val getRepositoryContentUseCase: GetRepositoryContentUseCase,
	private val parseFolderNameUseCase: ParseFolderNameUseCase,
	private val parseFileNameUseCase: ParseFileNameUseCase,
	private val router: RepositoryDetailRouter,
) : BaseViewModel() {

	var state by mutableStateOf<RepositoryDetailState>(RepositoryDetailState.Initial)
		private set

	fun init() {
		launch {
			state = RepositoryDetailState.Loading
			val contentList = getContent(repository)
			state = RepositoryDetailState.Content(contentList)
		}
	}

	private suspend fun getContent(repo: RepositoryTree): List<TypeOfFile> {
		val repositoryContent = getRepositoryContentUseCase(
			owner = repo.owner,
			repo = repo.repo,
			path = repo.path,
		)
		return getParsedFileNames(repositoryContent)
	}

	private fun getParsedFileNames(repositoryContent: List<RepositoryContent>): List<TypeOfFile> {
		val contentList = mutableListOf<TypeOfFile>()
		for (it in repositoryContent) {
			if (it.download_url != null) {
				contentList.add(
					TypeOfFile(
						name = parseFileNameUseCase(it.download_url!!),
						url = it.download_url!!,
						type = Type.FILE,
					)
				)
			} else {
				contentList.add(
					TypeOfFile(
						name = parseFolderNameUseCase(it.html_url),
						url = it.html_url,
						type = Type.FOLDER,
					)
				)
			}
		}
		return contentList
	}

	fun openFileDetail(item: TypeOfFile) {
		when (item.type) {
			Type.FILE   -> {
				router.openFile(FileUrlTransfer(URL(item.url)))
			}

			Type.FOLDER -> {
				val newPath = if (repository.path.isEmpty()) {
					item.name
				} else {
					repository.path + "/" + item.name
				}
				val repo = repository.copy(path = newPath)
				router.openFolder(repo)
			}
		}
	}

	fun appBarBackButtonClicked() {
		router.back()
	}
}