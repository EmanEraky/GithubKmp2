package remote.models



data class MainViewState(
    val data: List<GithubOwner>? = null,
    val isLoading: Boolean = false,
    val dataFailure: Exception? = null)