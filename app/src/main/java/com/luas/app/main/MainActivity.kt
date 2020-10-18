package com.luas.app.main

import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import com.luas.app.R
import com.luas.app.base.BaseActivity
import com.luas.app.databinding.ActivityMainBinding
import com.luas.app.main.stops.TramAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel by viewModel<MainViewModel>()
    private val tramAdapter = TramAdapter()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun setupViews() {
        binding.recyclerView.adapter = tramAdapter
    }

    override fun setupObservers() {
        viewModel.command.observe(this, Observer {
            when (it) {
                is MainViewModel.Command.ShowTram -> {
                    tramAdapter.submitList(it.viewModels)
                }
                is MainViewModel.Command.ShowError -> {
                    Toast.makeText(this, R.string.error_getting_forecast, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onSetupFinished() {
        viewModel.load()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.main_refresh) {
            viewModel.load()
        }
        return true
    }
}