package fitplan.planner.baseui.utils

import android.content.Context

fun dpFromPx(context: Context, px: Float): Float {
    return px / context.resources.displayMetrics.density
}