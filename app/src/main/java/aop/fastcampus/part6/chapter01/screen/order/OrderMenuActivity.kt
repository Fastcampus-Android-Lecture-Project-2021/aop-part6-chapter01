package aop.fastcampus.part6.chapter01.screen.order

import android.content.Context
import android.content.Intent
import aop.fastcampus.part6.chapter01.databinding.ActivityOrderMenuBinding
import aop.fastcampus.part6.chapter01.screen.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class OrderMenuActivity : BaseActivity<OrderMenuViewModel, ActivityOrderMenuBinding>() {

    override val viewModel by viewModel<OrderMenuViewModel>()

    override fun getViewBinding(): ActivityOrderMenuBinding = ActivityOrderMenuBinding.inflate(layoutInflater)

    companion object {
        fun newIntent(context: Context) = Intent(context, OrderMenuActivity::class.java).apply {

        }
    }

    override fun observeData() {

    }


}
