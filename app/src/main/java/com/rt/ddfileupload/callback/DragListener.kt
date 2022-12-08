package com.rt.ddfileupload.callback

import android.view.DragEvent
import android.view.View

class DragListener : View.OnDragListener {
    var isDropped = false
    override fun onDrag(view: View?, dragEvent: DragEvent?): Boolean {

        when (dragEvent?.action) {
            DragEvent.ACTION_DRAG_STARTED->{}

            DragEvent.ACTION_DRAG_ENTERED->{
//                v.setBackgroundColor(Color.LTGRAY)
            }

            DragEvent.ACTION_DRAG_EXITED->{
                //v.setBackgroundColor(Color.YELLOW)
            }
            DragEvent.ACTION_DROP->{

            }DragEvent.ACTION_DRAG_ENDED->{

            }
        }
        if (!isDropped) {
            val vw = dragEvent?.localState as View
            vw.visibility = View.VISIBLE
        }
        return true
    }
}