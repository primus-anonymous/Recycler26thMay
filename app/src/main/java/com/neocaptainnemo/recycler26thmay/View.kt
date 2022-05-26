package com.neocaptainnemo.recycler26thmay

import android.view.View

inline fun View.visibleIf(condition: () -> Boolean) {
    visibility = if (condition.invoke()) View.VISIBLE else View.GONE
}