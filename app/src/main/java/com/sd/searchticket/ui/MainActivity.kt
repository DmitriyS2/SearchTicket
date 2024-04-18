package com.sd.searchticket.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import com.sd.searchticket.R
import com.sd.searchticket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            bottomMenu.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.aviaTicket -> {
                        findNavController(R.id.nav_host_fragment)
                            .navigate(R.id.mainFragment)
                    }

                    R.id.hotels -> {

                        findNavController(R.id.nav_host_fragment)
                            .navigate(R.id.blankFragment)
                    }

                    R.id.shorter -> {
                        findNavController(R.id.nav_host_fragment)
                            .navigate(R.id.blankFragment)
                    }
                    R.id.notifications -> {

                        findNavController(R.id.nav_host_fragment)
                            .navigate(R.id.blankFragment)
                    }

                    R.id.profile -> {
                        findNavController(R.id.nav_host_fragment)
                            .navigate(R.id.blankFragment)
                    }

                }
                true
            }
        }
    }
}