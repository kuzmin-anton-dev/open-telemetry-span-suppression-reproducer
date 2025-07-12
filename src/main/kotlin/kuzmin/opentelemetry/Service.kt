package kuzmin.opentelemetry

import io.opentelemetry.api.trace.Tracer
import org.springframework.stereotype.Component

@Component
class Service(private val redisRepository: RedisRepository, private val tracer: Tracer) {

    fun performOperation() = tracer.withNewSpan("Service.performOperation") {
        redisRepository.storeValue("operation-key", "Operation performed")
    }
}
