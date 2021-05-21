package aop.fastcampus.part6.chapter01.screen.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {

    open fun fetchData(): Job = viewModelScope.launch {  }

}
