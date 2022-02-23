package hu.tuku13.spacexapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.tuku13.spacexapp.network.SpaceXApiService
import hu.tuku13.spacexapp.repository.SpaceXRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesSpaceXService(): SpaceXApiService {
        return SpaceXApiService.create()
    }

    @Provides
    @Singleton
    fun providesRepository(spaceXApi: SpaceXApiService): SpaceXRepository {
        return SpaceXRepository(spaceXApi)
    }

}
