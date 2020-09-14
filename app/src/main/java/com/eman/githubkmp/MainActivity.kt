package com.eman.githubkmp

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eman.githubkmp.models.GithubViewModel
import com.eman.githubkmp.network.GithubApi
import com.eman.githubkmp.R
import remote.models.GithubOwner
import remote.models.MainViewState
import java.lang.Exception

class MainActivity : AppCompatActivity() {


    private lateinit var adapter: SearchResultAdapter
    private lateinit var recyclerView:RecyclerView
    private lateinit var loadingView: ProgressBar


    private lateinit var mainViewModel: GithubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        loadingView = findViewById(R.id.loadingView)

        mainViewModel = GithubViewModel(GithubApi())
        observeOnViewState()

        adapter = SearchResultAdapter(layoutInflater)
        adapter.setHasStableIds(true)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        getUsers()
    }

    private fun getUsers() {

//mainViewModel.handle()

    }

    private fun observeOnViewState() {
//        mainViewModel.viewState.observe(this, Observer {
//            render(it)
//        })
    }

    private fun render(viewState: MainViewState?) {
        renderLoadingState(viewState?.isLoading ?: false)
        renderDataState(viewState?.data)
        renderErrorState(viewState?.dataFailure)
    }

    private fun renderLoadingState(loading: Boolean) {
        if (loading) {
            recyclerView.isEnabled = false
            loadingView.visibility = View.VISIBLE
        } else {
            recyclerView.isEnabled = true
            loadingView.visibility = View.GONE
        }
    }

    private fun renderDataState(dataState: List<GithubOwner>?) {
        if (!dataState.isNullOrEmpty()) {
            adapter.items = dataState
            adapter.notifyDataSetChanged()
        }
    }

    private fun renderErrorState(dataFailure: Exception?) {
        dataFailure?.let {
            Toast.makeText(this, dataFailure.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

}