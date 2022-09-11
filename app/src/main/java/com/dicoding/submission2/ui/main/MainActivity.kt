package com.dicoding.submission2.ui.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submission2.R
import com.dicoding.submission2.data.model.User
import com.dicoding.submission2.databinding.ActivityMainBinding
import com.dicoding.submission2.ui.main.detail.DetailUserActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: ViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    startActivity(it)
                }
            }
        })
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ViewModel::class.java]

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter

//            btnSearch.setOnClickListener {
//                searchUser()
//            }

//            etQuery.setOnKeyListener { v, keycode, event ->
//                if (event.action == KeyEvent.ACTION_DOWN && keycode == KeyEvent.KEYCODE_ENTER){
//                    searchUser()
//                    return@setOnKeyListener true
//                }
//                return@setOnKeyListener false
//            }
        }

        viewModel.getSearchUsers().observe(this) {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        }
    }

//    private fun searchUser(){
//        binding.apply {
//            val query = etQuery.text.toString()
//            if (query.isEmpty()) return
//            showLoading(true)
//            viewModel.setSearchUsers(query)
//        }
//    }

    private fun showLoading (state : Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
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
//                showLoading(true)
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
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
