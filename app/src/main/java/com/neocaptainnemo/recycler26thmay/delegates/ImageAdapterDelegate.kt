package com.neocaptainnemo.recycler26thmay.delegates

import android.widget.ImageView
import com.neocaptainnemo.recycler26thmay.AdapterItem
import com.neocaptainnemo.recycler26thmay.ImageItem
import com.neocaptainnemo.recycler26thmay.R

class ImageAdapterDelegate(private val imageLongClicked: () -> Unit) : AdapterDelegate<ImageItem> {

    private lateinit var img: ImageView

    override val layoutId: Int = R.layout.item_image

    override fun canHandleType(item: AdapterItem): Boolean = item is ImageItem

    override fun bind(item: ImageItem) {
        img.setImageResource(item.img)
    }

    override fun created(holder: DelegatesViewHolder) {
        img = holder.itemView.findViewById(R.id.img)

        img.setOnLongClickListener {
            imageLongClicked.invoke()
            true
        }
    }
}