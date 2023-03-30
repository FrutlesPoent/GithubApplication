package ru.curs.githubapplication.feature.authorization.presentation

sealed class AuthorizationState {

	object Initial : AuthorizationState()

	object Loading : AuthorizationState()

	object Content : AuthorizationState()
}
