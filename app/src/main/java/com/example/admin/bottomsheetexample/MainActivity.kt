package com.example.admin.bottomsheetexample

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity,LinearLayoutManager.VERTICAL))
            adapter = MainRecyclerAdapter().apply {
                itemClick = object : MainRecyclerAdapter.Click{
                    override fun onItemClick(text: String) { showSnackBar(text) }
                }
            }
        }

        val behavior = BottomSheetBehavior.from(bottomLayout).apply {
            setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback(){
                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    textView.text = when(newState){
                        BottomSheetBehavior.STATE_DRAGGING -> "STATE_DRAGGING"
                        BottomSheetBehavior.STATE_EXPANDED -> "STATE_EXPANDED"
                        BottomSheetBehavior.STATE_COLLAPSED -> "STATE_COLLAPSED"
                        BottomSheetBehavior.STATE_SETTLING -> "STATE_SETTLING"
                        else -> ""
                    }
                }
            })
        }

        showBtn.clicks().subscribe { behavior.state = BottomSheetBehavior.STATE_EXPANDED }
        hideBtn.clicks().subscribe { behavior.state = BottomSheetBehavior.STATE_COLLAPSED }
    }
    private fun showSnackBar(text : String){
        Snackbar.make(recycler,"clicked $text",Snackbar.LENGTH_SHORT).show()
    }
}
