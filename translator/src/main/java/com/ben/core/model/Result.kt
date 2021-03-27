package com.ben.core.model

sealed class Result<out T> {
    sealed class Success<T> : Result<T>() {
        abstract val value: T
        override fun toString() = "Success(${value})"

        class Value<T>(
            override val value: T
        ) : Success<T>()

        object Empty : Success<Nothing>() {
            override val value: Nothing get() = error("No value")
        }
    }

    sealed class Failure<T : Throwable>(open val error: T? = null) : Result<T>() {
        override fun toString() = "Failure(${error})"

        class Error(override val error: Throwable) : Failure<Throwable>(error)
    }
}