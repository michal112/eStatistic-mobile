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
import app.estat.mob.comp.spinner.FormSpinner
import app.estat.mob.component.ApplicationComponent
import app.estat.mob.db.entity.Bull
import app.estat.mob.event.StatusEvent
import app.estat.mob.mvp.core.MvpBaseFragment
import app.estat.mob.mvp.model.mateData
import app.estat.mob.mvp.presenter.action.AddMateFragmentPresenter
import app.estat.mob.mvp.util.ActivityUtils
import app.estat.mob.mvp.view.action.AddMateFragmentView
import app.estat.mob.ui.factory.ComponentFactory
import kotlinx.android.synthetic.main.fragment_add_mate.*

class AddMateFragment : MvpBaseFragment<AddMateFragmentPresenter, AddMateFragmentView>(), AddMateFragmentView {

    val TAG = javaClass.name

    lateinit var mBullsComponent :FormSpinner

    lateinit var mDateComponent :FormDatePicker

    companion object {
        private const val COW_PUBLIC_ID_KEY = "app.estat.mob.ui.action.AddMateFragment.COW_PUBLIC_ID_KEY"

        fun newInstance(cowPublicId :String?) :AddMateFragment? {
            val bundle = Bundle()
            bundle.putSerializable(COW_PUBLIC_ID_KEY, cowPublicId)
            val fragment = AddMateFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun createPresenter(context :Context?, applicationComponent :ApplicationComponent?) :AddMateFragmentPresenter {
        return AddMateFragmentPresenter(context, applicationComponent)
    }

    override fun onCreateView(inflater :LayoutInflater, container :ViewGroup?, savedInstanceState :Bundle?) :View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_add_mate, container, false)
    }

    override fun onViewCreated(view :View, savedInstanceState :Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cowPublicId = arguments?.get(COW_PUBLIC_ID_KEY) as String
        presenter.mCowPublicId = cowPublicId

        addComponents()
    }

    private fun addComponents() {
        mBullsComponent = ComponentFactory.getFormSpinnerComponent(activity, R.drawable.ic_bull, "fragment_add_mate_bull_hint", presenter.getSpinnerData(),true)
        mDateComponent = ComponentFactory.getFormDatePickerComponent(activity, R.drawable.ic_calendar, R.string.fragment_add_mate_date_hint)

        fragment_add_mate.insertView(mBullsComponent)
        fragment_add_mate.insertView(mDateComponent)
    }

    override fun onOptionsItemSelected(item :MenuItem?) :Boolean {
        when (item!!.itemId) {
            R.id.activity_add_mate_menu_done -> presenter.addMate(context,
                    mateData {
                        mDate = mDateComponent.date
                        mBull = mBullsComponent.selectedItem as Bull
                        mCow = presenter.mCow
                    }
            )
        }

        return true
    }

    override fun setActivityResult(status :StatusEvent.Status?) {
        when (status) {
            StatusEvent.Status.SUCCESS -> {
                activity!!.setResult(ActivityUtils.RESULT_MATE_SAVED)
                Log.d(TAG, "New mate saved successfully")
            }
            StatusEvent.Status.FAILURE -> {
                activity!!.setResult(ActivityUtils.RESULT_MATE_SAVE_ERROR)
                Log.e(TAG, "Mate save error")
            }
        }

        activity!!.finish()
    }
}