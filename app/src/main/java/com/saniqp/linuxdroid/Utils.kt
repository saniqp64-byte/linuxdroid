package com.saniqp.linuxdroid

import android.content.Context

class Utils {
    companion object {
        fun dp(context: Context, value: Float): Float {
            return value * context.resources.displayMetrics.density
        }
    }
}