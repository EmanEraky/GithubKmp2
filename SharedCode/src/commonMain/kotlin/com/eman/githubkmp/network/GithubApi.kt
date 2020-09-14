package com.eman.githubkmp.network

import com.eman.githubkmp.data.result.MainScreenResult
import io.ktor.client.*
import io.ktor.client.request.get

class GithubApi {
    private val httpClient = HttpClient()

    suspend fun getGithubUsers(): MainScreenResult {
        return try {
            MainScreenResult.DataState(data = httpClient.get { baseUrl + "/users" })
        } catch (e: Exception) {
            MainScreenResult.ErrorState(e)
        }

    }

    companion object {
        private const val baseUrl = "https://api.github.com"
    }
}