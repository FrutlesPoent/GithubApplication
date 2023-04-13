package ru.curs.githubapplication.data.auth.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import net.openid.appauth.AuthorizationService
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.curs.githubapplication.data.auth.AppAuth
import ru.curs.githubapplication.data.auth.data.repository.AuthRepositoryImpl
import ru.curs.githubapplication.data.auth.domain.AuthRepository
import ru.curs.githubapplication.data.auth.domain.entity.TokenStorage
import ru.curs.githubapplication.data.auth.domain.usecase.GetAuthRequestUseCase
import ru.curs.githubapplication.data.auth.domain.usecase.PerformTokenRequestUseCase
import ru.curs.githubapplication.data.auth.retrofit.interceptor.AuthorizationFailedInterceptor
import ru.curs.githubapplication.data.auth.retrofit.interceptor.AuthorizationInterceptor
import ru.curs.githubapplication.data.auth.retrofit.provider.provideOkHttpClient
import ru.curs.githubapplication.data.auth.retrofit.provider.provideRetrofit

private const val AUTH_INTERCEPTOR = "authInterceptor"
private const val AUTH_FAILED_INTERCEPTOR = "authFailedInterceptor"

val appAuthModule = module {
	single { AppAuth }
	single<AuthRepository> { AuthRepositoryImpl() }
	factory { GetAuthRequestUseCase(get()) }
	factory { PerformTokenRequestUseCase(get()) }
}

val networkModule = module {

	single {
		Moshi.Builder()
			.add(KotlinJsonAdapterFactory())
			.build()
	}
	single(named(AUTH_INTERCEPTOR)) { AuthorizationInterceptor() }
	single(named(AUTH_FAILED_INTERCEPTOR)) { AuthorizationFailedInterceptor(get(), TokenStorage) }

	single {
		provideOkHttpClient(
			interceptors = listOf(
				AuthorizationInterceptor(),
				AuthorizationFailedInterceptor(AuthorizationService(androidContext()), TokenStorage),
			)
		)
	}
	single {
		provideRetrofit(
			okHttpClient = get(),
			moshi = get(),
			url = "https://api.github.com/"
		)
	}
}