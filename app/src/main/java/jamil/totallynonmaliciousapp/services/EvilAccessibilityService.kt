package jamil.totallynonmaliciousapp.services

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.os.SystemClock
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast
import android.R.attr.packageNames
import android.R.attr.notificationTimeout
import android.accessibilityservice.AccessibilityServiceInfo



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


        val builder = GestureDescription.Builder()
        val path = Path()

        path.moveTo(993.0f, 288.0f)
//        path.lineTo(500.0f, 100.0f)
        val stroke = GestureDescription.StrokeDescription(path, 3000, 100)
        builder.addStroke(stroke)

        dispatchGesture(builder.build(), null, null)
    }


}