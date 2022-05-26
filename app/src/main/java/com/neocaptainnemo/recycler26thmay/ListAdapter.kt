package com.neocaptainnemo.recycler26thmay

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ListAdapter(
    private val numberItemClicked: ((item: String) -> Unit)? = null,
    private val imageLongClicked: ((item: ImageItem) -> Unit)? = null,
    private val itemDragged: ((viewHolder: RecyclerView.ViewHolder) -> Unit)? = null,
    private val numberRemoved: ((position: Int) -> Unit)? = null,

    ) :
    ListAdapter<AdapterItem, RecyclerView.ViewHolder>(object :
        DiffUtil.ItemCallback<AdapterItem>() {
        override fun areItemsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean =
            oldItem.key == newItem.key

        override fun areContentsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean =
            oldItem == newItem

    }) {

    companion object {
        private const val TYPE_NUMBER = 1
        private const val TYPE_IMAGE = 2
        private const val TYPE_HEADER = 3
    }

    override fun getItemViewType(position: Int): Int = when (currentList[position]) {
        is NumberItem -> TYPE_NUMBER
        is ImageItem -> TYPE_IMAGE
        is HeaderItem -> TYPE_HEADER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {

            TYPE_NUMBER -> NumberViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_number, parent, false)
            )
            TYPE_IMAGE -> ImageViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
            )

            TYPE_HEADER -> HeaderViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
            )

            else -> throw IllegalStateException("Cannot be here")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is NumberViewHolder -> {

                val item = currentList[position] as NumberItem

                with(holder) {
                    txt.text = item.text
                    up.visibleIf { position != 0 }
                    down.visibleIf { position != currentList.size - 1 }
                }
            }

            is ImageViewHolder -> {

                val item = currentList[position] as ImageItem

                holder.img.setImageResource(item.img)
            }

            is HeaderViewHolder -> {

                val item = currentList[position] as HeaderItem

                holder.header.text = item.header
            }


        }

    }

    fun itemRemoved(position: Int) {
        currentList.removeAt(position)
    }

    fun itemsMoved(from: Int, to: Int) {
        Collections.swap(currentList, from, to)
    }

    inner class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txt: TextView = itemView.findViewById(R.id.text)

        val up: ImageView = itemView.findViewById(R.id.up)


        val down: ImageView = itemView.findViewById(R.id.down)


        init {
            itemView.setOnClickListener {

                (currentList[adapterPosition] as? NumberItem)?.let {
                    numberItemClicked?.invoke(it.text)
                }
            }

            itemView.findViewById<ImageView>(R.id.drag).setOnTouchListener { _, motionEvent ->

                if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                    itemDragged?.invoke(this)
                }

                false
            }


            itemView.findViewById<ImageView>(R.id.delete).setOnClickListener {
                numberRemoved?.invoke(adapterPosition)
            }

            itemView.findViewById<ImageView>(R.id.add).setOnClickListener {

                currentList.add(adapterPosition, NumberItem("id", UUID.randomUUID().toString()))

                notifyItemInserted(adapterPosition)
            }

            itemView.findViewById<ImageView>(R.id.update).setOnClickListener {

//                currentList[adapterPosition] = NumberItem(UUID.randomUUID().toString())

                notifyItemChanged(adapterPosition)
            }

            up.setOnClickListener {

                Collections.swap(currentList, adapterPosition, adapterPosition - 1)

                up.visibleIf { adapterPosition - 1 != 0 }

                notifyItemMoved(adapterPosition, adapterPosition - 1)

                notifyItemChanged(adapterPosition)
            }

            down.setOnClickListener {

                Collections.swap(currentList, adapterPosition, adapterPosition + 1)

                down.visibleIf { adapterPosition + 1 != currentList.size - 1 }

                notifyItemMoved(adapterPosition, adapterPosition + 1)

                notifyItemChanged(adapterPosition)

            }

        }
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val img: ImageView = itemView.findViewById(R.id.img)

        init {
            img.setOnLongClickListener {
                (currentList[adapterPosition] as? ImageItem)?.let {
                    imageLongClicked?.invoke(it)
                }
                true
            }
        }
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val header: TextView = itemView.findViewById(R.id.header)
    }

}