package aop.fastcampus.part6.chapter01.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import aop.fastcampus.part6.chapter01.screen.OnBackPressedListener

fun Fragment.findNavigator(): NavController =
    NavHostFragment.findNavController(this)

fun FragmentActivity.onFragmentBackPressed(): Boolean {
    supportFragmentManager.fragments.forEach {
        if (it is NavHostFragment) {
            it.childFragmentManager.fragments.forEach { childFragment ->
                if (childFragment is OnBackPressedListener) {
                    return childFragment.onBackPressed()
                }
            }
        }
    }
    return true
}

fun <T> Fragment.getNavigationResult(key: String) =
    findNavigator().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)

fun <T> Fragment.setNavigationResult(result: T, key: String) {
    findNavigator().previousBackStackEntry?.savedStateHandle?.set(key, result)
}
