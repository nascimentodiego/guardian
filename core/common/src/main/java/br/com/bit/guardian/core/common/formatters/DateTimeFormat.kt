package br.com.bit.guardian.core.common.formatters

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter

const val PATTERN_YEAR_MONTH_DAY = "yyyy-MM-dd"
const val PATTERN_DAY_MONTH_BR = "dd/MM"
const val PATTERN_FULL_DATE_TIME_1 = "dd 'de' MMMM 'de' yyyy, HH:mm"
const val PATTERN_FULL_DATE_TIME_2 = "EEEE, dd 'de' MMMM 'de' yyyy, HH:mm"
const val PATTERN_FULL_DATE_TIME_3 = "dd/MM/yyyy 'ás' HH'h'mm"
const val PATTERN_DATE_BR_1 = "dd/MM/yyyy"
const val PATTERN_DATE_BR_2 = "dd/MM/yy"
const val PATTERN_TIME_BR_1 = "HH:mm"

internal val UTC_3 = TimeZone.of("UTC+3")

fun String.toDateTimeFormat(pattern: String = PATTERN_TIME_BR_1): String {
    val dateTime = this.toInstant().toLocalDateTime(TimeZone.currentSystemDefault())

    return DateTimeFormatter.ofPattern(pattern).format(dateTime.toJavaLocalDateTime())
}

fun Instant.toDateTimeFormat(
    pattern: String = PATTERN_FULL_DATE_TIME_3,
    timeZone:TimeZone = TimeZone.currentSystemDefault()): String {
    val dateTime = this.toLocalDateTime(timeZone)
    return DateTimeFormatter.ofPattern(pattern).format(dateTime.toJavaLocalDateTime())
}

fun Instant.toDateTimeBrTimeZoneFormat(pattern: String = PATTERN_FULL_DATE_TIME_3): String {
    val dateTime = this.toLocalDateTime(UTC_3)

    return DateTimeFormatter.ofPattern(pattern).format(dateTime.toJavaLocalDateTime())
}