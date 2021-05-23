package aop.fastcampus.part6.chapter01.screen.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.IntegerRes
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

typealias NavigateAction = Pair<Int, Bundle>

abstract class BaseViewModel: ViewModel() {

    var isInitialized: Boolean = false

    val navigateLiveData = MutableLiveData<NavigateAction>()

    protected var stateBundle: Bundle? = null

    open fun fetchData(): Job = viewModelScope.launch {  }

    protected fun navigateTo(@IdRes navigateId: Int, bundle: Bundle? = null) {
        navigateLiveData.value = NavigateAction(navigateId, bundle ?: bundleOf())
    }

    open fun storeState(stateBundle: Bundle) {
        this.stateBundle = stateBundle

    }



}
