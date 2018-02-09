/*package com.neu.info7255.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.neu.info7255.demo.InsurancePlan;
import com.neu.info7255.demo.repository.InsurancePlanRepository;


@Configuration
public class PlanRedisConfig {

	@Bean
	public InsurancePlanRepository repository(RedisTemplate<String, InsurancePlan> redisTemplate){
		return new InsurancePlanRepository(redisTemplate);
		
	}
	@Bean
	public RedisTemplate<String, InsurancePlan> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, InsurancePlan> template = new RedisTemplate();

		template.setConnectionFactory(redisConnectionFactory);

		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		RedisSerializer<InsurancePlan> personSerializer = new Jackson2JsonRedisSerializer<>(InsurancePlan.class);

		template.setKeySerializer(stringSerializer);
		template.setValueSerializer(personSerializer);
		template.setHashKeySerializer(stringSerializer);
		template.setHashValueSerializer(personSerializer);

		return template;
	}
}
*/