package com.sunnyweather.android.ui.adapter

import android.animation.TypeEvaluator
import com.sunnyweather.android.bean.Angle

class AngleEvaluator : TypeEvaluator<Angle> {

    override fun evaluate(fraction: Float, startValue: Angle?, endValue: Angle?): Angle? {

        val angle: Float =
            startValue?.plus((startValue.angle - endValue?.angle!!) * fraction) as Float

        return Angle(angle)

    }

}