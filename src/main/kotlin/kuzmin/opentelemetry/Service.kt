package kuzmin.opentelemetry

import io.opentelemetry.instrumentation.annotations.WithSpan
import org.springframework.stereotype.Component
import io.opentelemetry.api.trace.SpanKind

@Component
class Service(private val client: Client) {

    @WithSpan(kind = SpanKind.CLIENT)
    fun performOperation() = client.performOperation()
}
