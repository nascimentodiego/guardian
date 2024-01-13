package br.com.bit.guardian.core.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    @SerialName("ui_behaviour")
    val uiBehaviour: UiBehaviour,
    val message: String
)