package app.estat.mob.comp.recycler

import android.support.annotation.StringRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import app.estat.mob.comp.R
import app.estat.mob.comp.text.FormTextView
import app.estat.mob.db.entity.FormItem
import java.text.SimpleDateFormat
import java.util.*

class FormAdapter<T : FormItem>(list: MutableList<T>, formItemClickListener: FormItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface FormItemClickListener {
        fun onClick(position :Int)
    }

    val mList :MutableList<T> = list

    lateinit var mType :FormRecyclerView.Type

    var mNameIcon :Int = 0

    var mCalendarIcon :Int = 0

    val mFormItemClickListener = formItemClickListener

    var mDeleteButton :Int = 0

    class FormHolder(itemView: View, formItemClickListener: FormItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val ICON_ALPHA = 0.54f

        val mButton :ImageButton = itemView.findViewById(R.id.component_recycler_view_form_item_delete)

        val mName :FormTextView = itemView.findViewById(R.id.component_recycler_view_form_item_name)

        val mDate :FormTextView = itemView.findViewById(R.id.component_recycler_view_form_item_date)

        init {
            mButton.setOnClickListener { formItemClickListener.onClick(adapterPosition) }
        }

        fun bindName(@StringRes hint :Int, name :String) {
            mName.setHint(hint)
            mName.setText(name)
        }

        fun bindNameIcon(icon :Int) {
            mName.setIcon(icon)
        }

        fun bindDate(@StringRes hint :Int, date :Date) {
            mDate.setHint(hint)
            mDate.setText(SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(date))
        }

        fun bindDateIcon(icon :Int) {
            mDate.setIcon(icon)
        }

        fun bindDeleteButton(icon :Int) {
            mButton.setImageResource(icon)
            mButton.alpha = ICON_ALPHA
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FormHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.component_recycler_view_form, parent, false), mFormItemClickListener)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FormHolder) {
            when {
                FormRecyclerView.Type.COW == mType -> {
                    holder.bindName(R.string.component_form_recycler_view_name_hint, mList[position].getCowName())
                    holder.bindDate(R.string.component_form_recycler_view_date_hint, mList[position].getDate())
                }
                FormRecyclerView.Type.BULL == mType -> {
                    holder.bindName(R.string.component_form_recycler_view_name_hint, mList[position].getBullName())
                    holder.bindDate(R.string.component_form_recycler_view_date_hint, mList[position].getDate())
                }
                FormRecyclerView.Type.LACTATION == mType -> {
                    holder.bindName(R.string.component_form_recycler_view_number_hint, mList[position].getNumber())
                    holder.bindDate(R.string.component_form_recycler_view_lactation_date_hint, mList[position].getDate())
                }
            }
            holder.bindNameIcon(mNameIcon)
            holder.bindDateIcon(mCalendarIcon)
            holder.bindDeleteButton(mDeleteButton)
        }
    }
}