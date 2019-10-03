package com.nosti.animo.ui.common

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatAsSimple(): String {
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.US)
    return dateFormat.format(this)
}

fun Date.formatAsYearMonthDay(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    return dateFormat.format(this)
}

fun Date.formatAsWeekDay(): String {
    val dateFormat = SimpleDateFormat("EEE", Locale.US)
    return dateFormat.format(this)
}

fun Date.formatAsWeekDayNumber(): Int {
    val calendar = Calendar.getInstance(Locale.US)
    calendar.time = this
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    // Return Monday as day 1, Tuesday as 2, etc.
    return if (dayOfWeek == 1) 7 else dayOfWeek - 1
}

fun Date.formatAsDay(): String {
    val dateFormat = SimpleDateFormat("d", Locale.US)
    return dateFormat.format(this)
}

fun Date.formatAsMonth(): String {
    val dateFormat = SimpleDateFormat("MMMM", Locale.US)
    return dateFormat.format(this)
}

fun Date.toMMddyyyyDateFormat(): String {
    val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)
    return dateFormat.format(this)
}

fun Date.toHourFormat(): String {
    val dateFormat = SimpleDateFormat("h:mm a", Locale.US)
    return dateFormat.format(this)
}

fun Date.toHourFormatWithTimeZone(timeZoneId: String?): String {
    val dateFormat = SimpleDateFormat("h:mm a", Locale.US)
    timeZoneId?.let {
        dateFormat.timeZone = TimeZone.getTimeZone(timeZoneId)
    }
    return dateFormat.format(this)
}

fun String.toDate(): Date {
    return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse(this)
}

fun String.toDateWithTimeZoneAsGmt(): Date {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    dateFormat.timeZone = TimeZone.getTimeZone("GMT")
    return dateFormat.parse(this)
}

fun Date.toShortDateFormat(): String {
    val dateFormat = SimpleDateFormat("dd MMM", Locale.US)
    return dateFormat.format(this)
}

fun Date.formatAsMMMdd(): String {
    val dateFormat = SimpleDateFormat("MMM dd", Locale.US)
    return dateFormat.format(this)
}