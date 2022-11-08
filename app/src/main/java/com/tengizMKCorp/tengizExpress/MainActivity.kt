package com.tengizMKCorp.tengizExpress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        binding.floatingHomeButton.alpha = 0.9f
        binding.floatingHomeButton.setOnClickListener {
            navController.navigateUp() // to clear previous navigation history
            navController.navigate(R.id.homeFragment)
        }
    }


}