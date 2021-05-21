package aop.fastcampus.part6.chapter01.screen.base

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class ModelListViewModel: BaseViewModel() {

    override fun fetchData(): Job = viewModelScope.launch {  }

}
