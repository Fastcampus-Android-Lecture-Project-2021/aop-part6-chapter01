package aop.fastcampus.part6.chapter01.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import aop.fastcampus.part6.chapter01.R
import aop.fastcampus.part6.chapter01.databinding.ActivitySingleBinding
import aop.fastcampus.part6.chapter01.extensions.onFragmentBackPressed

class SingleActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingleBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.navHostContainer
        ) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onBackPressed() {
        if (onFragmentBackPressed()) {
            super.onBackPressed()
        }
    }

}
