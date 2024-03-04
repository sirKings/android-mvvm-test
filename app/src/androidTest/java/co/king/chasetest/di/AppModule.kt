package co.king.chasetest.di

import android.app.Application
import androidx.room.Room
import co.king.chasetest.planetDetail.data.local.PlanetDetailDatabase
import co.king.chasetest.planetDetail.data.remote.PlanetDetailApi
import co.king.chasetest.planetDetail.data.remote.dto.FilmDto
import co.king.chasetest.planetDetail.data.remote.dto.ResidentDto
import co.king.chasetest.planetList.data.local.PlanetDao
import co.king.chasetest.planetList.data.local.PlanetDatabase
import co.king.chasetest.planetList.data.remote.PlanetListApi
import co.king.chasetest.planetList.data.remote.dto.PlanetDto
import co.king.chasetest.planetList.data.remote.dto.PlanetListResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.runBlocking
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object AppModuleTest {

    @Singleton
    @Provides
    fun providePlanetListApi(): PlanetListApi{
        val p = PlanetDto(
            "Test", "", "", "", "", "", "", "", "",
            emptyList(),
            emptyList(), "", "https://swapi.dev/api/planets/2/"
        )
        val api =  mock(PlanetListApi::class.java)
        runBlocking {
            `when`(api.fetchPlanetList(null)).thenReturn(
                Response.success(
                    PlanetListResponse(
                        1, null, null, listOf(
                            p
                        )
                    )
                )
            )
        }
        return api
    }



    @Singleton
    @Provides
    fun providePlanetDetailApi(): PlanetDetailApi{
        val f = FilmDto("Test", 2, "", "", "", "", "https://swapi.dev/api/film/2/")
        val r = ResidentDto("Test", "", "", "", "", "", "","", "https://swapi.dev/api/people/2/")
        val api = mock(PlanetDetailApi::class.java)
        runBlocking {
            `when`(api.getFilm(2)).thenReturn(
                Response.success(f)
            )
            `when`(api.getPeople(2)).thenReturn(
                Response.success(r)
            )
        }
        return api
    }



    @Provides
    @Singleton
    fun provideDatabase(app: Application): PlanetDatabase =
        Room.inMemoryDatabaseBuilder(
            app, PlanetDatabase::class.java,
        ).build()

    @Provides
    @Singleton
    fun provideCurrencyDao(db: PlanetDatabase): PlanetDao = db.planetDao

    @Provides
    @Singleton
    fun providePlanetDetailDatabase(app: Application): PlanetDetailDatabase =
        Room.inMemoryDatabaseBuilder(
            app, PlanetDetailDatabase::class.java,
        ).build()

    @Provides
    @Singleton
    fun provideFilmDao(db: PlanetDetailDatabase) = db.filmDao

    @Provides
    @Singleton
    fun provideResidentDao(db: PlanetDetailDatabase) = db.residentDao

}