package aop.fastcampus.part6.chapter01.extensions

import android.content.res.Resources

fun Float.fromDpToPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}
