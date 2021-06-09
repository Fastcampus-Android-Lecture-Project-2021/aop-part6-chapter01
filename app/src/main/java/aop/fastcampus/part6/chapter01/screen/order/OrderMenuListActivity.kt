package aop.fastcampus.part6.chapter01.screen.order

import android.content.Context
import android.content.Intent
import androidx.core.view.isGone
import androidx.core.view.isVisible
import aop.fastcampus.part6.chapter01.databinding.ActivityOrderMenuListBinding
import aop.fastcampus.part6.chapter01.model.restaurant.FoodModel
import aop.fastcampus.part6.chapter01.screen.base.BaseActivity
import aop.fastcampus.part6.chapter01.widget.adapter.ModelRecyclerAdapter
import aop.fastcampus.part6.chapter01.widget.adapter.listener.order.OrderMenuListListener
import org.koin.android.viewmodel.ext.android.viewModel

class OrderMenuListActivity : BaseActivity<OrderMenuListViewModel, ActivityOrderMenuListBinding>() {

    override val viewModel by viewModel<OrderMenuListViewModel>()

    override fun getViewBinding(): ActivityOrderMenuListBinding = ActivityOrderMenuListBinding.inflate(layoutInflater)

    companion object {
        fun newIntent(context: Context) = Intent(context, OrderMenuListActivity::class.java)
    }

    private val adapter by lazy {
        ModelRecyclerAdapter<FoodModel, OrderMenuListViewModel>(listOf(), viewModel, adapterListener = object : OrderMenuListListener {
            override fun onRemoveItem(model: FoodModel) {
                viewModel.removeOrderMenu(model)
            }
        })
    }

    override fun initViews() = with(binding) {
        toolbar.setNavigationOnClickListener { finish() }
        recyclerVIew.adapter = adapter
    }

    override fun observeData() = viewModel.orderMenuState.observe(this) {
        when(it) {
            is OrderMenuState.Loading -> {
                binding.progressBar.isVisible = true
            }
            is OrderMenuState.Success -> {
                binding.progressBar.isGone = true
                adapter.submitList(it.restaurantFoodModelList)
            }
            is OrderMenuState.Error -> {

            }
            else -> Unit
        }
    }


}
