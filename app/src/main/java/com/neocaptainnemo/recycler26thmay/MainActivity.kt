package com.neocaptainnemo.recycler26thmay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list: RecyclerView = findViewById(R.id.list)

        val helper = ItemTouchHelper(ItemTouchHelperCallBack({

            adapter.itemRemoved(it)

            adapter.notifyItemRemoved(it)

        }, { from, to ->

            with(adapter) {
                itemsMoved(from, to)
                notifyItemMoved(from, to)
                notifyItemChanged(from)
                notifyItemChanged(to)
            }
        }))

        val data = mutableListOf(
            HeaderItem("id1", "Headline 1"),
            NumberItem("id2", "1"),
            NumberItem("id3", "2"),
            ImageItem("id4", R.drawable.image),
            NumberItem("id5", "3"),
            NumberItem("id6", "4"),
            HeaderItem("id7", "Headline 2"),
            NumberItem("id8", "5"),
            ImageItem("id9", R.drawable.image),
            NumberItem("id10", "6"),
            ImageItem("id11", R.drawable.ic_launcher_background),
        )


        adapter = ListAdapter({
            Snackbar.make(list, it, Snackbar.LENGTH_LONG).show()
        }, {
            Snackbar.make(list, it.img.toString(), Snackbar.LENGTH_LONG).show()
        }, {
            helper.startDrag(it)
        }, {


            adapter.submitList(listOf(
                NumberItem("id5", "3"),
                NumberItem("id6", "4"),
                HeaderItem("id7", "Headline 2"),

                ))

        }).apply {
            submitList(data)
        }

        list.adapter = adapter

        helper.attachToRecyclerView(list)

        list.addItemDecoration(ListItemDecorator())

    }
}