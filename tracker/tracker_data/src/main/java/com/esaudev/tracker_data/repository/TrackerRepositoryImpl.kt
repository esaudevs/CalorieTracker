package com.esaudev.tracker_data.repository

import com.esaudev.tracker_data.local.dao.TrackerDao
import com.esaudev.tracker_data.mapper.toTrackableFood
import com.esaudev.tracker_data.mapper.toTrackedFood
import com.esaudev.tracker_data.mapper.toTrackedFoodEntity
import com.esaudev.tracker_data.remote.api.OpenFoodApi
import com.esaudev.tracker_domain.model.TrackableFood
import com.esaudev.tracker_domain.model.TrackedFood
import com.esaudev.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val trackerDao: TrackerDao,
    private val api: OpenFoodApi
): TrackerRepository {

    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {

            val searchDto = api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )

            Result.success(value = searchDto.products.mapNotNull { it.toTrackableFood() })

        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(exception = e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        trackerDao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        trackerDao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return trackerDao.getFoodsForDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).map { entities ->
            entities.map { it.toTrackedFood() }
        }
    }

}