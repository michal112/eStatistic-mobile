package app.estat.mob.ui.action

import android.content.Context
import android.os.Bundle
import app.estat.mob.R
import app.estat.mob.component.ApplicationComponent
import app.estat.mob.mvp.presenter.action.AddLactationActivityPresenter
import app.estat.mob.mvp.view.action.AddLactationActivityView

class AddLactationActivity : ActionActivity<AddLactationActivityPresenter, AddLactationActivityView>(), AddLactationActivityView {

    lateinit var mCowPublicId :String

    override fun getTitleRes() :Int {
        return R.string.activity_add_lactation_toolbar_title
    }

    override fun createPresenter(context: Context?, applicationComponent: ApplicationComponent?): AddLactationActivityPresenter {
        return AddLactationActivityPresenter(context, applicationComponent)
    }

    override fun getMenuResId(): Int {
        return R.menu.activity_add_lactation_menu
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        mCowPublicId = intent.getStringExtra(VALUE_KEY)
        addFragment(R.id.activity_action_container, AddLactationFragment.newInstance(mCowPublicId), false)
    }
}