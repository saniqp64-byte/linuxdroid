package com.saniqp.linuxdroid.windows

import android.content.Context
import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.saniqp.linuxdroid.R
import com.saniqp.linuxdroid.Utils
import com.saniqp.linuxdroid.Window

class Fastfetch(
    context: Context,
    mainLayout: ConstraintLayout
) : Window(context, mainLayout) {

    fun start_fastfetch(){
        val windowLayout = start_window(Utils.dp(context, 340f), Utils.dp(context, 220f))

        val image = ImageView(context).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                Utils.dp(context, 90f).toInt(),
                Utils.dp(context, 90f).toInt()
            ).apply {
                leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID
                topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                leftMargin = Utils.dp(context, 20f).toInt()
                topMargin = Utils.dp(context, 45f).toInt()
            }

            val andoridVersionId = resources.getIdentifier(
                "_android_fastfetch_${Build.VERSION.SDK_INT - 20}",
                "drawable",
                context.packageName
            )
            setImageResource(andoridVersionId)
        }

        val text_version = TextView(context).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                Utils.dp(context, 180f).toInt(),
                Utils.dp(context, 30f).toInt()
            ).apply {
                leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID
                topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                leftMargin = Utils.dp(context, 130f).toInt()
                topMargin = Utils.dp(context, 45f).toInt()
            }

            textSize = 13f
            typeface = androidx.core.content.res.ResourcesCompat.getFont(context, R.font.pixel)
            text = "version: ${Build.VERSION.SDK_INT - 20}"
        }

        val text_model = TextView(context).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                Utils.dp(context, 200f).toInt(),
                Utils.dp(context, 30f).toInt()
            ).apply {
                leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID
                topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                leftMargin = Utils.dp(context, 130f).toInt()
                topMargin = Utils.dp(context, 80f).toInt()
            }

            textSize = 10f
            typeface = androidx.core.content.res.ResourcesCompat.getFont(context, R.font.pixel)
            text = "device: ${Build.MODEL}"
        }

        val text_device_name = TextView(context).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                Utils.dp(context, 180f).toInt(),
                Utils.dp(context, 30f).toInt()
            ).apply {
                leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID
                topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                leftMargin = Utils.dp(context, 130f).toInt()
                topMargin = Utils.dp(context, 115f).toInt()
            }

            textSize = 10f
            typeface = androidx.core.content.res.ResourcesCompat.getFont(context, R.font.pixel)
            text = "name: ${Build.HARDWARE}"
        }

        windowLayout.addView(image)
        windowLayout.addView(text_version)
        windowLayout.addView(text_model)
        windowLayout.addView(text_device_name)
    }
}