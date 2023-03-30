package ru.curs.githubapplication.shared.authorization.data.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.curs.githubapplication.shared.authorization.data.api.GithubAuthorizationApi

internal val AuthorizationApiModule = module {
	factory {
		Retrofit.Builder()
			.baseUrl("https://api.github.com")
			.addConverterFactory(MoshiConverterFactory.create(moshi))
			.build()
			.create(GithubAuthorizationApi::class.java)
	}
}

val authorizationModule = listOf(
	AuthorizationApiModule,
)

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
