package com.tengizMKCorp.tengizExpress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.tengizMKCorp.tengizExpress.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val navController = binding.fragmentContainerView.getFragment<NavHostFragment>().navController
        setContentView(binding.root)

        val bottomNavigation = binding.bottomNavigationView
        bottomNavigation.setupWithNavController(navController)

        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    navController.navigateUp() // to clear previous navigation history
                    navController.navigate(R.id.homeFragment)
                     true
                }
                R.id.search -> {
                    navController.navigateUp()
                    navController.navigate(R.id.searchFragment)
                     true
                }
                R.id.cart -> {
                    navController.navigateUp()
                    navController.navigate(R.id.cartFragment)
                     true
                }
                R.id.account -> {
                    navController.navigateUp()
                    navController.navigate(R.id.accountFragment)
                     true
                } else -> true
            }
        }
    }




}