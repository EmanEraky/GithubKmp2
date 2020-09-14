package  com.eman.githubkmp.data.result

import remote.models.GithubOwner

sealed class MainScreenResult  {
    object LoadingState : MainScreenResult()
    data class DataState(val data: List<GithubOwner>) : MainScreenResult()
    data class ErrorState(val error: Exception) : MainScreenResult()
}