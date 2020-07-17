package com.sunnyweather.android.ui

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sunnyweather.android.SunnyWeatherApplication
import com.sunnyweather.android.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.include_weather_sun.view.*
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class SunView:View {

    private lateinit var path: Path
    private lateinit var paint: Paint
    private lateinit var cPaint: Paint
    private var angle = -1f
    private var r: Float = -1f

    private var mHeight: Int = 0
    private var mWidth: Int = 0
    private lateinit var canvas: Canvas

    constructor(context: Context) : super(context){

    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val w = getSize(widthMeasureSpec)
        val h = getSize(heightMeasureSpec)
        setMeasuredDimension(
            MeasureSpec.makeMeasureSpec(w, MeasureSpec.getMode(widthMeasureSpec)),
            MeasureSpec.makeMeasureSpec(h, MeasureSpec.getMode(heightMeasureSpec))
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        r = min(mWidth / 2f, mHeight.toFloat())
        mWidth -= (2 * r / 10).toInt()
        r = min(mWidth / 2f, mHeight.toFloat())
    }

    private fun getSize(measureSpec: Int): Int {
        val mod = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        return when (mod) {
            MeasureSpec.AT_MOST -> size
            MeasureSpec.EXACTLY -> size
            MeasureSpec.UNSPECIFIED -> 100
            else -> 0
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        init()
        canvas?.translate(
            mWidth / 2.0f + paint.strokeWidth + r / 10,
            mHeight + paint.strokeWidth - r / 10
        )
        if (angle == -1f) {
            angle = 0f
            drawArc(canvas,angle)
        } else {
            drawArc(canvas,angle)
        }
    }

    private fun drawArc(canvas: Canvas?, angle: Float) {
        val rectF = RectF(-r, -r, r, r)
        canvas?.drawArc(rectF, -180f, angle, false, paint)
        canvas?.drawCircle(
            r * cos(Math.toRadians(180.0 - angle.toDouble())).toFloat(),
            -r * sin(Math.toRadians(180.0 - angle.toDouble())).toFloat(),
            r / 10,
            cPaint
        )
    }

    private fun init() {
        paint = Paint()
        path = Path()
        paint.color = Color.RED
        paint.strokeWidth = 4f
        paint.style = Paint.Style.STROKE
        cPaint = Paint()
        cPaint.color = Color.YELLOW
        cPaint.style = Paint.Style.FILL_AND_STROKE
        cPaint.strokeWidth = 4f
    }

    fun startAnimator(angle: Float) {
        val animator = ValueAnimator.ofFloat(0f, angle)
        animator.addUpdateListener { animation ->
            this.angle = animation.animatedValue as Float
            invalidate()
        }
        animator.duration = 1500
        animator.start()
    }

}