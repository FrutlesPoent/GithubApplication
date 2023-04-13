package ru.curs.githubapplication.feature.authorization.presentation

sealed class AuthorizationState {

	data class Content(val isLoginPageOpen: Boolean) : AuthorizationState()

	object Error : AuthorizationState()
}
