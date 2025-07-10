package kuzmin.opentelemetry

import io.opentelemetry.api.trace.Tracer
import org.springframework.stereotype.Component

@Component
class Service(private val client: Client, private val tracer: Tracer) {

    fun performOperation() = tracer.withNewSpan("Service.performOperation") {
        client.performOperation()
    }
}
