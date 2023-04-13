package ru.curs.githubapplication.userprofile.presentation

interface UserProfileRouter {

	fun openDetails(username: String)

	fun openFollowers()

	fun openFollowing()
}