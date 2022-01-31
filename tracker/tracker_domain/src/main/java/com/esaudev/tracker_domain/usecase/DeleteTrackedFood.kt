package com.esaudev.tracker_domain.usecase

import com.esaudev.tracker_domain.model.TrackedFood
import com.esaudev.tracker_domain.repository.TrackerRepository

class DeleteTrackedFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(trackedFood: TrackedFood) {

        return repository.deleteTrackedFood(food = trackedFood)
    }

}