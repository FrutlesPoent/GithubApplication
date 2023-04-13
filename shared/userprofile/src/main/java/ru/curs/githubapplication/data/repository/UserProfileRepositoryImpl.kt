package ru.curs.githubapplication.data.repository

import ru.curs.githubapplication.data.datasource.UserProfileDataSource
import ru.curs.githubapplication.domain.entity.UserProfile
import ru.curs.githubapplication.domain.repository.UserProfileRepository

class UserProfileRepositoryImpl(
	private val dataSource: UserProfileDataSource,
) : UserProfileRepository {

	override suspend fun getUserProfile(): UserProfile {
		println(dataSource.getUserProfile())
		return dataSource.getUserProfile()
	}
}