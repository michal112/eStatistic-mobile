package app.estat.mob.comp.recycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

class FormRecyclerView : RecyclerView {

    lateinit var mType :Type

    var mNameIcon :Int = 0

    var mCalendarIcon :Int = 0

    var mDeleteButton :Int = 0

    constructor(context :Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)

        if (adapter is FormAdapter<*>) {
            adapter.mNameIcon = mNameIcon
            adapter.mCalendarIcon = mCalendarIcon
            adapter.mType = mType
            adapter.mDeleteButton = mDeleteButton
        }
    }

    enum class Type {
        BULL,
        COW,
        LACTATION
    }
}