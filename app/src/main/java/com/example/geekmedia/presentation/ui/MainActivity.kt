package com.example.geekmedia.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.geekmedia.R
import com.example.geekmedia.core.CheckInternetConnection
import com.example.geekmedia.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkInternetConnection()

        setupNavController()
        setupNavView()
        supportActionBar?.hide()
    }

    private fun checkInternetConnection() {
            val connectivity = CheckInternetConnection(this)
            connectivity.observe(this) {
                if (it == true) {
                    binding.noInternetConnection.root.visibility = View.GONE
                    binding.navView.visibility = View.VISIBLE
                    binding.navHostFragment.visibility = View.VISIBLE
                } else {
                    binding.noInternetConnection.root.visibility = View.VISIBLE
                    binding.navView.visibility = View.GONE
                    binding.navHostFragment.visibility = View.GONE
                }
            }

    }

    private fun setupNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_search_menu,
                R.id.navigation_favorites_news,
                R.id.navigation_profile,
                R.id.postFragment,
                R.id.geekMediaInformationFragment
            )
        )

        setupActionBarWithNavController(navController!!, appBarConfiguration)
    }

    private fun setupNavView(){
        val navView: BottomNavigationView = binding.navView
        navView.setupWithNavController(navController!!)

        navController!!.addOnDestinationChangedListener{_, destination, _ ->
            if(destination.id == R.id.postFragment || destination.id == R.id.geekMediaInformationFragment) {
                navView.visibility = View.GONE
            } else {
                navView.visibility = View.VISIBLE
            }
        }

    }

}