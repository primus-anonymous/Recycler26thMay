package com.neocaptainnemo.recycler26thmay

import androidx.annotation.DrawableRes

sealed class AdapterItem(val key: String)


data class NumberItem(val id: String, val text: String) : AdapterItem(id)
data class HeaderItem(val id: String, val header: String) : AdapterItem(id)
data class ImageItem(val id: String, @DrawableRes val img: Int) : AdapterItem(id)
