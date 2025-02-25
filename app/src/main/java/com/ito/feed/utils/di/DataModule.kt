package com.ito.feed.utils.di

import android.app.Application
import com.ito.feed.utils.api.FeedsApi
import com.ito.feed.utils.datastore.DataStore
import com.ito.feed.utils.datastore.DataStoreImpl
import com.ito.feed.utils.interactor.FeedsInteractor
import com.ito.feed.utils.interactor.FeedsInteractorImpl
import com.ito.feed.utils.repository.FeedsRepository
import com.ito.feed.utils.repository.FeedsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Singleton
    @Provides
    fun provideAppDataStore(app: Application): DataStore {
        return DataStoreImpl(app)
    }

    @Singleton
    @Provides
    fun provideFeedsRepository(api: FeedsApi, dataStore: DataStore) : FeedsRepository {
        return FeedsRepositoryImpl(api, dataStore)
    }

    @Singleton
    @Provides
    fun provideFeedsInteractor(feedsRepository: FeedsRepository) : FeedsInteractor {
        return FeedsInteractorImpl(feedsRepository)
    }
}
