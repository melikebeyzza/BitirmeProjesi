package com.example.bitirmeprojesi.di

import com.example.bitirmeprojesi.data.datasource.MoviesDataSource
import com.example.bitirmeprojesi.data.repo.MoviesRepository
import com.example.bitirmeprojesi.retrofit.AppiUtils
import com.example.bitirmeprojesi.retrofit.MoviesDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideMoviesRepository(mds:MoviesDataSource):MoviesRepository{
        return MoviesRepository(mds)
    }
    @Provides
    @Singleton
    fun provideMoviesDataSource(mdo:MoviesDAO):MoviesDataSource{
        return MoviesDataSource(mdo)
    }
    @Provides
    @Singleton
    fun provideMoviesDao():MoviesDAO{
        return AppiUtils.getMoviesDao()
    }

}