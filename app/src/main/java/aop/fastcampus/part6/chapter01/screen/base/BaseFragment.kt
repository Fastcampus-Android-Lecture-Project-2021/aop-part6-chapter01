package aop.fastcampus.part6.chapter01.screen.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import aop.fastcampus.part6.chapter01.screen.OnBackPressedListener
import kotlinx.coroutines.Job

abstract class BaseFragment<VM: BaseViewModel, VB: ViewBinding>: Fragment(), OnBackPressedListener {

    abstract val viewModel: VM

    protected lateinit var binding: VB

    private var rootView: View? = null

    abstract fun getViewBinding(): VB

    private lateinit var fetchJob: Job

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initState()
    }

    open fun initState() {
        arguments?.let {
            viewModel.storeState(it)
        }
        initViews()
        if (viewModel.isInitialized.not()) {
            fetchJob = viewModel.fetchData()
            viewModel.isInitialized = true
        }
        observeData()
    }

    open fun initViews() = Unit

    open fun observeData() {
        viewModel.navigateLiveData.observe(viewLifecycleOwner) { navigateAction ->
            navigateAction?.let { (navigateId, bundle) ->
                findNavController().navigate(navigateId, bundle)
                viewModel.navigateLiveData.value = null
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (fetchJob.isActive) {
            fetchJob.cancel()
        }
    }

    protected fun setResultAndNavigateUp(requestKey: String, bundle: Bundle) {
        setFragmentResult(requestKey, bundle)
        findNavController().navigateUp()
    }

    override fun onBackPressed(): Boolean {
        findNavController().navigateUp()
        return false
    }


}
