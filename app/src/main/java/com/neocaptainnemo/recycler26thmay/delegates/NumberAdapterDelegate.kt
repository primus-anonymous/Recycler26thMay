package com.neocaptainnemo.recycler26thmay.delegates

import android.widget.ImageView
import android.widget.TextView
import com.neocaptainnemo.recycler26thmay.AdapterItem
import com.neocaptainnemo.recycler26thmay.NumberItem
import com.neocaptainnemo.recycler26thmay.R
import com.neocaptainnemo.recycler26thmay.visibleIf

class NumberAdapterDelegate : AdapterDelegate<NumberItem> {

    private lateinit var txt: TextView

    private lateinit var up: ImageView

    private lateinit var down: ImageView

    override val layoutId: Int = R.layout.item_number

    override fun canHandleType(item: AdapterItem): Boolean = item is NumberItem

    override fun created(holder: DelegatesViewHolder) {

        val itemView = holder.itemView

        txt = itemView.findViewById(R.id.text)

        up = itemView.findViewById(R.id.up)

        down = itemView.findViewById(R.id.down)
    }

    override fun bind(item: NumberItem) {

        txt.text = item.text
//        up.visibleIf { position != 0 }
//        down.visibleIf { position != currentList.size - 1 }
    }

}