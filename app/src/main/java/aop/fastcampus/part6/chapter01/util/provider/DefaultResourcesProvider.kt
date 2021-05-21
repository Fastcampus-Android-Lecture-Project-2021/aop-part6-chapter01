package aop.fastcampus.part6.chapter01.util.provider

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.StringRes

class DefaultResourcesProvider(
    private val context: Context
) : ResourcesProvider {

    override fun getString(@StringRes resId: Int): String = context.getString(resId)

    override fun getString(@StringRes resId: Int, vararg formatArgs: Any): String = context.getString(resId, *formatArgs)

    override fun getColorStateList(@ColorRes resId: Int) = context.getColorStateList(resId)

}
