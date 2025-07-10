package kuzmin.opentelemetry

import io.opentelemetry.api.GlobalOpenTelemetry
import io.opentelemetry.api.trace.Tracer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

    @Bean
    fun tracer(): Tracer = GlobalOpenTelemetry.getTracer("kuzmin.opentelemetry")
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}