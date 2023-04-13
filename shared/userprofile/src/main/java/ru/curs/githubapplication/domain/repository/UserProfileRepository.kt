package ru.curs.githubapplication.domain.repository

import ru.curs.githubapplication.domain.entity.UserProfile

interface UserProfileRepository {

	suspend fun getUserProfile(): UserProfile
}