package kuzmin.opentelemetry

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class Controller(private val service: Service) {

    @GetMapping("/operation")
    fun performOperation() = service.performOperation()
}
