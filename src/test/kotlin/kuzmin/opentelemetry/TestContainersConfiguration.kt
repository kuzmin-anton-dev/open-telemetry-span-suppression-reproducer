package kuzmin.opentelemetry

import com.redis.testcontainers.RedisContainer
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class TestContainersConfiguration {

    @Bean
    fun jaegerContainer() = JaegerContainer(DockerImageName.parse("jaegertracing/all-in-one:1.68.0"))

    @Bean
    @ServiceConnection
    fun redisContainer() = RedisContainer(DockerImageName.parse("redis:8.0.3"))

    class JaegerContainer(imageName: DockerImageName) : GenericContainer<JaegerContainer>(imageName) {

        private companion object {
            private const val WEB_UI_PORT = 16686
            private const val HTTP_PROTOBUF_PORT = 4318
        }

        init {
            withExposedPorts(WEB_UI_PORT, HTTP_PROTOBUF_PORT)
            addFixedExposedPort(WEB_UI_PORT, WEB_UI_PORT)
            addFixedExposedPort(HTTP_PROTOBUF_PORT, HTTP_PROTOBUF_PORT)
            waitingFor(Wait.forListeningPort())
        }
    }
}
