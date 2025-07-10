package kuzmin.opentelemetry

import io.opentelemetry.api.trace.SpanKind
import io.opentelemetry.api.trace.Tracer
import kotlin.use

fun <T> Tracer.withNewSpan(name: String, block: () -> T): T {
    val span = this.spanBuilder(name)
        .setSpanKind(SpanKind.CLIENT)
        .startSpan()
    return try {
        span.makeCurrent().use { block() }
    } finally {
        span.end()
    }
}
