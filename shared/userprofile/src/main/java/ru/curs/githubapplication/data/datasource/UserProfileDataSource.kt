package ru.curs.githubapplication.data.datasource

import ru.curs.githubapplication.domain.entity.UserProfile

interface UserProfileDataSource {

	suspend fun getUserProfile(): UserProfile
}