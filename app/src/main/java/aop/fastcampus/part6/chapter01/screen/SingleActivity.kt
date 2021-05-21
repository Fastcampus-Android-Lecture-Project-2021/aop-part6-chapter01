package aop.fastcampus.part6.chapter01.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import aop.fastcampus.part6.chapter01.R
import aop.fastcampus.part6.chapter01.databinding.ActivitySingleBinding

class SingleActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}
