package com.neocaptainnemo.recycler26thmay.delegates

import android.widget.TextView
import com.neocaptainnemo.recycler26thmay.AdapterItem
import com.neocaptainnemo.recycler26thmay.HeaderItem
import com.neocaptainnemo.recycler26thmay.R

class HeaderAdapterDelegates : AdapterDelegate<HeaderItem> {

    private lateinit var header: TextView

    override val layoutId: Int = R.layout.item_header

    override fun canHandleType(item: AdapterItem): Boolean = item is HeaderItem

    override fun bind(item: HeaderItem) {
        header.text = item.header
    }

    override fun created(holder: DelegatesViewHolder) {
        header = holder.itemView.findViewById(R.id.header)
    }
}