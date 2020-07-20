package app.estat.mob.ui.action

import android.content.Context
import android.os.Bundle
import app.estat.mob.R
import app.estat.mob.component.ApplicationComponent
import app.estat.mob.mvp.presenter.action.AddMateActivityPresenter
import app.estat.mob.mvp.view.action.AddMateActivityView

class AddMateActivity : ActionActivity<AddMateActivityPresenter, AddMateActivityView>(), AddMateActivityView {

    lateinit var mCowPublicId :String

    override fun getTitleRes() :Int {
        return R.string.activity_add_mate_toolbar_title
    }

    override fun createPresenter(context :Context?, applicationComponent :ApplicationComponent?) :AddMateActivityPresenter {
        return AddMateActivityPresenter(context, applicationComponent)
    }

    override fun getMenuResId() :Int {
        return R.menu.activity_add_mate_menu
    }

    override fun onCreate(savedInstanceState :Bundle?) {
        super.onCreate(savedInstanceState)

        mCowPublicId = intent.getStringExtra(VALUE_KEY)
        addFragment(R.id.activity_action_container, AddMateFragment.newInstance(mCowPublicId), false)
    }
}