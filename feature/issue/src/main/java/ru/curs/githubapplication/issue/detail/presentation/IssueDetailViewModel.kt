package ru.curs.githubapplication.issue.detail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.curs.githubapplication.component.ui.mvvm.BaseViewModel
import ru.curs.githubapplication.domain.entity.IssueEntity
import ru.curs.githubapplication.shared.issue.domain.usecase.AddCommentUseCase
import ru.curs.githubapplication.shared.issue.domain.usecase.GetIssueCommentsUseCase
import java.net.URL
import java.net.URLDecoder

class IssueDetailViewModel(
	private val issue: IssueEntity,
	private val getIssueCommentsUseCase: GetIssueCommentsUseCase,
	private val addCommentUseCase: AddCommentUseCase,
	private val router: IssueDetailRouter,
) : BaseViewModel() {

	var state by mutableStateOf<IssueDetailState>(IssueDetailState.Initial)
		private set

	fun init() {
		if (state == IssueDetailState.Initial) {
			launch {
				state = IssueDetailState.Loading
				val decodedEntity = issue.decodeEntity()
				val issueComments = getIssueCommentsUseCase(owner = decodedEntity.user.login, repo = decodedEntity.parseUrlForRepoName(), decodedEntity.number)
				state = IssueDetailState.Content(issue = decodedEntity, issueComments = issueComments)
			}
		}
	}

	private fun update() {
		launch {
			val decodedEntity = issue.decodeEntity()
			val issueComments = getIssueCommentsUseCase(owner = decodedEntity.user.login, repo = decodedEntity.parseUrlForRepoName(), decodedEntity.number)
			state = IssueDetailState.Content(issue = decodedEntity, issueComments = issueComments)
		}
	}

	fun addComment(text: String) {
		launch {
			val decodedEntity = issue.decodeEntity()
			addCommentUseCase(
				owner = decodedEntity.user.login,
				repo = decodedEntity.parseUrlForRepoName(),
				body = text,
				issueNumber = decodedEntity.number
			)
		}
		update()
	}

	private fun IssueEntity.decodeEntity(): IssueEntity {
		val decodedAvatar = decodeAvatar()
		val decodedRepository = decodeRepository()
		return this.copy(
			user = this.user.copy(avatar_url = decodedAvatar),
			repository_url = decodedRepository,
		)
	}

	private fun IssueEntity.parseUrlForRepoName(): String {
		return this.repository_url.split("/").last()
	}

	private fun decodeAvatar(): String {
		return decode(issue.user.avatar_url)
	}

	private fun decodeRepository(): String {
		return decode(issue.repository_url)
	}

	private fun decode(url: String): String =
		URL(URLDecoder.decode(url)).toString()

	fun back() {
		router.back()
	}
}