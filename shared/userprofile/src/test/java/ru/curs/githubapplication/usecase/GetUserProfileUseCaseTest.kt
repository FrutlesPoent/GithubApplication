package ru.curs.githubapplication.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import ru.curs.githubapplication.domain.entity.UserProfile
import ru.curs.githubapplication.domain.repository.UserProfileRepository
import ru.curs.githubapplication.domain.usecase.GetUserProfileUseCase

class GetUserProfileUseCaseTest {

	private val repository: UserProfileRepository = mock()

	private val usecase = GetUserProfileUseCase(repository)

	private val userProfile = UserProfile(
		id = 1,
		login = "any",
		name = "any",
		avatar_url = "url",
		following = 0,
		followers = 0,
		public_repos = 1,
		owned_private_repos = 2,
	)

	@OptIn(ExperimentalCoroutinesApi::class)
	@Test
	fun `invoke get user profile use case EXPECT get user profile`() = runTest {
		whenever(repository.getUserProfile()) doReturn userProfile

		val result = usecase.invoke()

		Assert.assertEquals(userProfile, result)
	}
}