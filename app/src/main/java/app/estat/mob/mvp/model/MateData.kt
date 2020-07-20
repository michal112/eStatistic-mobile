package app.estat.mob.mvp.model

import app.estat.mob.db.entity.Bull
import app.estat.mob.db.entity.Cow
import app.estat.mob.db.entity.Mate
import java.util.*

data class MateData(var mDate :Date? = null, var mBull :Bull? = null, val mPublicId :String = UUID.randomUUID().toString(), var mCow :Cow? = null)

fun mateData(action: MateData.() -> Unit) : MateData {
    val mateData = MateData()
    mateData.apply(action)
    return mateData
}

fun MateData.getMate() : Mate {
    val mate = Mate()
    mate.publicId = mPublicId
    mate.setDate(mDate)
    mate.cow = mCow
    mate.cowId = mCow?.id
    mate.bull = mBull
    mate.bullId = mBull?.id
    return mate
}