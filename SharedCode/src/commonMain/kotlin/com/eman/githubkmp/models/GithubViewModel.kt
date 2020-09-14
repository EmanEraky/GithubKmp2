package com.eman.githubkmp.models

import com.eman.githubkmp.data.action.MainScreenAction
import com.eman.githubkmp.data.result.MainScreenResult
import com.eman.githubkmp.network.GithubApi
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import remote.models.MainViewState

class GithubViewModel(private val gitRepository: GithubApi) : ViewModel() {

    val internalViewState: MainViewState = MainViewState()

     suspend fun handle() {
        reduce(gitRepository.getGithubUsers())
    }

    fun reduce(result: MainScreenResult): MainViewState {
        return when (result) {
            is MainScreenResult.DataState -> internalViewState.copy(
                data = result.data,
                dataFailure = null,
                isLoading = false
            )
            is MainScreenResult.LoadingState -> internalViewState.copy(isLoading = true)
            is MainScreenResult.ErrorState -> internalViewState.copy(
                dataFailure = result.error,
                isLoading = false
            )
        }
    }

}