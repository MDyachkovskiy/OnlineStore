package com.test.application.remote_data.api

import com.test.application.remote_data.dto.CatalogueDTO
import com.test.application.remote_data.utils.CATALOGUE_URL
import retrofit2.http.GET

interface CatalogueService {
    @GET(CATALOGUE_URL)
    suspend fun getCatalogueData(): CatalogueDTO
}