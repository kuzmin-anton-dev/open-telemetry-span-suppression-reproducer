package kuzmin.opentelemetry

import io.opentelemetry.api.trace.Tracer
import org.springframework.stereotype.Component

@Component
class Client(private val tracer: Tracer) {

    fun performOperation() = tracer.withNewSpan("Client.performOperation") {
        "Operation performed"
    }
}
