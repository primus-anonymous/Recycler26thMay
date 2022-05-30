package com.neocaptainnemo.recycler26thmay.delegates

import com.neocaptainnemo.recycler26thmay.AdapterItem

interface AdapterDelegate<T: AdapterItem> {

    val layoutId: Int

    fun canHandleType(item: AdapterItem): Boolean

    fun bind(item: T)

    fun created(holder: DelegatesViewHolder)
}