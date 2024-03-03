package co.king.chasetest.di

import android.app.Application
import androidx.room.Room
import co.king.chasetest.planetList.data.local.PlanetDao
import co.king.chasetest.planetList.data.local.PlanetDatabase
import co.king.chasetest.planetList.data.remote.PlanetListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    const val BASE_URL = "https://swapi.dev/api/"

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Singleton
    @Provides
    fun providePlanetListApi(): PlanetListApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()


    @Provides
    @Singleton
    fun provideDatabase(app: Application): PlanetDatabase =
        Room.databaseBuilder(
            app, PlanetDatabase::class.java,
            "planet.db"
        ).build()

    @Provides
    @Singleton
    fun provideCurrencyDao(db: PlanetDatabase): PlanetDao = db.planetDao

}