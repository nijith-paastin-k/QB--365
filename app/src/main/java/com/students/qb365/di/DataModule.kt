package com.students.qb365.di

import android.content.Context
import com.students.qb365.api.ApiService
import com.students.qb365.ui.auth.repo.AuthRepo
import com.students.qb365.ui.dashboard.repo.MainRepo
import com.students.qb365.ui.subject_mode.repo.SubjectModeRepo
import com.students.qb365.utils.Constants
import com.students.qb365.utils.SharedPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @ApplicationScope
    @Singleton
    @Provides
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

    @Provides
    fun provideInterceptors(@ApplicationContext context: Context): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request: Request =
                    chain.request()
                        .newBuilder()
                        .header(
                            "Authorization",
                            "Bearer " + SharedPrefs.getString(
                                context,
                                SharedPrefs.ACCESS_TOKEN,
                                "AsJ4[pR3=bM5^gJ0]pS6.gI2\$hV5*uSA"
                            )
                        )
                        .build()
                chain.proceed(request)
            }
            .addInterceptor(logging)
            .build()

    }

    @Provides
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideAuthRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideAuthRepository(api: ApiService): AuthRepo {
        return AuthRepo(api)
    }

    @Provides
    fun provideMainRepository(api: ApiService): MainRepo {
        return MainRepo(api)
    }

    @Provides
    fun provideSubjectDemoRepository(api: ApiService): SubjectModeRepo {
        return SubjectModeRepo(api)
    }


}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope