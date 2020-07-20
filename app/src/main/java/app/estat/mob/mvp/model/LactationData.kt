package app.estat.mob.mvp.model

import app.estat.mob.db.entity.Cow
import app.estat.mob.db.entity.Lactation
import java.util.*

data class LactationData(var mDate :Date? = null, var mPublicId :String = UUID.randomUUID().toString(), var mNumber :String? = null, var mCow : Cow?  = null)

fun lactationData(action: LactationData.() -> Unit) :LactationData {
    val lactationData = LactationData()
    lactationData.apply(action)
    return lactationData
}

fun LactationData.getLactation() :Lactation {
    var lactation = Lactation()
    lactation.cow = mCow
    lactation.cowId = mCow?.id
    lactation.setDate(mDate)
    lactation.setNumber(mNumber)
    lactation.publicId = mPublicId
    return lactation
}