package br.com.bit.guardian.feature.reports.ui.widget.model

import br.com.bit.guardian.feature.reports.R

enum class DeviceAction(val resourceName: Int) {
    OPEN_CLOSE(R.string.reports_device_open_close_action),
    ALARM(R.string.reports_device_alarm_action),
    DETECT(R.string.reports_device_detect_action),
    UNKNOW(R.string.reports_device_unknown_action);

    companion object {
        fun findByNameSafe(action: String): DeviceAction {
            return when (action) {
                OPEN_CLOSE.name -> OPEN_CLOSE
                ALARM.name -> ALARM
                DETECT.name -> DETECT
                else -> UNKNOW
            }
        }
    }
}