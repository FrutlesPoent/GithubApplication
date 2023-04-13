package ru.curs.githubapplication.data.auth.retrofit

//object Network {
//
//	private var okHttpClient: OkHttpClient? = null
//	private var retrofit: Retrofit? = null
//
//	val githubApi: GithubApi
//		get() = retrofit?.create() ?: error("retrofit is not initialized")
//
//	fun start(context: Context) {
//		okHttpClient = OkHttpClient()
//			.newBuilder()
//			.addNetworkInterceptor(AuthorizationInterceptor())
//			.addNetworkInterceptor(AuthorizationFailedInterceptor(AuthorizationService(context), TokenStorage))
//			.build()
//		retrofit = Retrofit.Builder()
//			.baseUrl("https://api.github.com/")
//			.addConverterFactory(MoshiConverterFactory.create())
//			.client(okHttpClient!!)
//			.build()
//	}
//}