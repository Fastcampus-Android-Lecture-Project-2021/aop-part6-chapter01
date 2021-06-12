package aop.fastcampus.part6.chapter01.screen

import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import aop.fastcampus.part6.chapter01.R
import aop.fastcampus.part6.chapter01.databinding.ActivityMainBinding
import aop.fastcampus.part6.chapter01.screen.base.BaseActivity
import aop.fastcampus.part6.chapter01.screen.home.HomeFragment
import aop.fastcampus.part6.chapter01.screen.like.RestaurantLikeListFragment
import aop.fastcampus.part6.chapter01.screen.my.MyFragment
import aop.fastcampus.part6.chapter01.util.event.MenuChangeEventBus
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(), BottomNavigationView.OnNavigationItemSelectedListener {

    override val viewModel by viewModel<MainViewModel>()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private val menuChangeEventBus by inject<MenuChangeEventBus>()

    override fun initState() {
        super.initState()
        lifecycleScope.launch {
            menuChangeEventBus.changeMenu(MainTabMenu.HOME)
        }
    }

    override fun initViews() = with(binding) {
        bottomNav.setOnNavigationItemSelectedListener(this@MainActivity)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_home -> {
                showFragment(HomeFragment.newInstance(), HomeFragment.TAG)
                true
            }
            R.id.menu_like -> {
                showFragment(RestaurantLikeListFragment.newInstance(), RestaurantLikeListFragment.TAG)
                true
            }
            R.id.menu_my -> {
                showFragment(MyFragment.newInstance(), MyFragment.TAG)
                true
            }
            else -> false
        }
    }

    fun goToTab(mainTabMenu: MainTabMenu) {
        binding.bottomNav.selectedItemId = mainTabMenu.menuId
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        val findFragment = supportFragmentManager.findFragmentByTag(tag)
        supportFragmentManager.fragments.forEach { fm ->
            supportFragmentManager.beginTransaction().hide(fm).commitAllowingStateLoss()
        }
        findFragment?.let {
            supportFragmentManager.beginTransaction().show(it).commitAllowingStateLoss()
        } ?: kotlin.run {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, fragment, tag)
                .commitAllowingStateLoss()
        }
    }

    override fun observeData() {
        lifecycleScope.launch {
            menuChangeEventBus.mainTabMenuFlow.collect {
                goToTab(it)
            }
        }
    }

}

enum class MainTabMenu(@IdRes val menuId: Int) {
    HOME(R.id.menu_home), MY(R.id.menu_my)
}
