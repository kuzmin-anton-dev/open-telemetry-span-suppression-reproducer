package kuzmin.opentelemetry

import io.opentelemetry.instrumentation.annotations.WithSpan
import org.springframework.stereotype.Component
import io.opentelemetry.api.trace.SpanKind

@Component
class Client {

    @WithSpan(kind = SpanKind.CLIENT)
    fun performOperation() = "Operation performed"
}
