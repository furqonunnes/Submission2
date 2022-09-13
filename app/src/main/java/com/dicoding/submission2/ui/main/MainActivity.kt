package com.dicoding.submission2.ui.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submission2.R
import com.dicoding.submission2.data.model.User
import com.dicoding.submission2.databinding.ActivityMainBinding
import com.dicoding.submission2.ui.detail.DetailActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: ViewModel
    private lateinit var adapter: UserAdapter

    private fun showSelectedUser(user: User) {
        Toast.makeText(this, "Kamu Memilih " + user.login, Toast.LENGTH_SHORT).show()
        val moveWithObjectIntent = Intent(this@MainActivity, DetailActivity::class.java)
        moveWithObjectIntent.putExtra(DetailActivity.EXTRA_USERNAME, user.login)
        startActivity(moveWithObjectIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ViewModel::class.java]

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter

            adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
                override fun onItemClicked(data: User) {
                    showSelectedUser(data)
                }
            })
        }

        viewModel.getSearchUsers().observe(this) {
            if (it.isNullOrEmpty()) {
                Toast.makeText(this, "Username tidak ditemukan", Toast.LENGTH_SHORT).show()
                showLoading(false)
            } else {
                adapter.setList(it)
                showLoading(false)
                showText(false)
            }
        }
    }

     private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun showText(state: Boolean) {
        if (state) {
            binding.tvMain.visibility = View.VISIBLE
        } else {
            binding.tvMain.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.setSearchUsers(query)
                showLoading(true)
                searchView.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return true
    }
}
