package kuzmin.opentelemetry

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class RedisRepository(
    private val redisTemplate: RedisTemplate<String, String>
) {
    fun storeValue(key: String, value: String): String {
        redisTemplate.opsForValue().set(key, value)
        return "Value stored in Redis with key: ${redisTemplate.opsForValue().get(key)}"
    }
}