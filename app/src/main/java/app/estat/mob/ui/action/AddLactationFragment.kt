package app.estat.mob.ui.action

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import app.estat.mob.R
import app.estat.mob.comp.picker.FormDatePicker
import app.estat.mob.comp.text.FormEditText
import app.estat.mob.component.ApplicationComponent
import app.estat.mob.event.StatusEvent
import app.estat.mob.mvp.core.MvpBaseFragment
import app.estat.mob.mvp.model.lactationData
import app.estat.mob.mvp.presenter.action.AddLactationFragmentPresenter
import app.estat.mob.mvp.util.ActivityUtils
import app.estat.mob.mvp.view.action.AddLactationFragmentView
import app.estat.mob.ui.factory.ComponentFactory
import kotlinx.android.synthetic.main.fragment_add_lactation.*

class AddLactationFragment : MvpBaseFragment<AddLactationFragmentPresenter, AddLactationFragmentView>(), AddLactationFragmentView {

    val TAG = javaClass.name

    lateinit var mNumberComponent :FormEditText

    lateinit var mDateComponent :FormDatePicker

    companion object {
        private const val COW_PUBLIC_ID_KEY = "app.estat.mob.ui.action.AddLactationFragment.COW_PUBLIC_ID_KEY"

        fun newInstance(cowPublicId :String?) :AddLactationFragment? {
            val bundle = Bundle()
            bundle.putSerializable(COW_PUBLIC_ID_KEY, cowPublicId)
            val fragment = AddLactationFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun createPresenter(context :Context?, applicationComponent :ApplicationComponent?) :AddLactationFragmentPresenter {
        return AddLactationFragmentPresenter(context, applicationComponent)
    }

    override fun onCreateView(inflater :LayoutInflater, container :ViewGroup?, savedInstanceState :Bundle?) :View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_add_lactation, container, false)
    }

    override fun onViewCreated(view :View, savedInstanceState :Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cowPublicId = arguments?.get(COW_PUBLIC_ID_KEY) as String
        presenter.mCowPublicId = cowPublicId

        addComponents()
    }

    private fun addComponents() {
        mNumberComponent = ComponentFactory.getFormEditTextComponent(activity, R.drawable.ic_number, R.string.fragment_add_lactation_number_hint)
        mDateComponent = ComponentFactory.getFormDatePickerComponent(activity, R.drawable.ic_calendar, R.string.fragment_add_mate_date_hint)

        fragment_add_lactation.insertView(mNumberComponent)
        fragment_add_lactation.insertView(mDateComponent)
    }

    override fun onOptionsItemSelected(item :MenuItem?) :Boolean {
        when (item!!.itemId) {
            R.id.activity_add_lactation_menu_done -> presenter.addLactation(context,
                    lactationData {
                        mDate = mDateComponent.date
                        mNumber = mNumberComponent.text
                        mCow = presenter.mCow
                    }
            )
        }

        return true
    }

    override fun setActivityResult(status :StatusEvent.Status?) {
        when (status) {
            StatusEvent.Status.SUCCESS -> {
                activity!!.setResult(ActivityUtils.RESULT_LACTATION_SAVED)
                Log.d(TAG, "New lactation saved successfully")
            }
            StatusEvent.Status.FAILURE -> {
                activity!!.setResult(ActivityUtils.RESULT_LACTATION_SAVE_ERROR)
                Log.e(TAG, "Lactation save error")
            }
        }

        activity!!.finish()
    }
}