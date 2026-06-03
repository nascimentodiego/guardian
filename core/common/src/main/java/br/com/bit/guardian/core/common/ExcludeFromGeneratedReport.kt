package br.com.bit.guardian.core.common

@Retention(AnnotationRetention.BINARY) // = CLASS retention
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class ExcludeFromGeneratedReport