package kuzmin.opentelemetry

import org.springframework.boot.fromApplication
import org.springframework.boot.with

fun main(args: Array<String>) {
    fromApplication<Application>()
        .with(TestContainersConfiguration::class)
        .run(*args)
}
