package br.com.bit.guardian.feature.reports.data.datasource.remote.response

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Device(
    val name: String = "",
    val type: String = "",
    val uuid: String = ""
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "type" to type,
            "uuid" to uuid
        )
    }
}
