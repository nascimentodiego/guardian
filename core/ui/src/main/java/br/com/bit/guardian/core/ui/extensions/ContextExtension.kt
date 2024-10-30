package br.com.bit.guardian.core.ui.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.intentDeepLink(link: String) = Intent(
    packageName,
    Uri.parse("guardian:/$link")
).apply {
    setPackage(packageName)
}


