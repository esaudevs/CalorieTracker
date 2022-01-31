package com.esaudev.tracker_domain.di

import com.esaudev.core.domain.preferences.Preferences
import com.esaudev.tracker_domain.repository.TrackerRepository
import com.esaudev.tracker_domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        trackerRepository: TrackerRepository,
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFood = TrackFood(repository = trackerRepository),
            searchFood = SearchFood(repository = trackerRepository),
            getFoodsForDate = GetFoodsForDate(repository = trackerRepository),
            deleteTrackedFood = DeleteTrackedFood(repository = trackerRepository),
            calculateMealNutrients = CalculateMealNutrients(preferences = preferences)
        )
    }

}