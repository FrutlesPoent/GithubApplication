package ru.curs.githubapplication.repositorydetail.presentation

import ru.curs.githubapplication.domain.entity.RepositoryTree
import ru.curs.githubapplication.repositorydetail.entity.FileUrlTransfer

interface RepositoryDetailRouter {

	fun openFile(url: FileUrlTransfer)

	fun openFolder(contentList: RepositoryTree)

	fun back()
}