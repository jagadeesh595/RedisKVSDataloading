package com.ns.dataloading;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@EnableRedisRepositories
@Configuration
public class Redisconfiguration {

    @Value("${redis.host}")
    private String host;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.port}")
    private int port;

    @Value("${redis.jedis.pool.max-total}")
    private int maxTotal;
    @Value("${redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${redis.jedis.pool.min-idle}")
    private int minIdle;

    @Bean
    public StringRedisSerializer stringRedisSerializer() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        return stringRedisSerializer;
    }

    @Bean
    public JedisClientConfiguration getJedisClientConfiguration() {
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder JedisPoolingClientConfigurationBuilder =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration
                        .builder();

        GenericObjectPoolConfig GenericObjectPoolConfig = new GenericObjectPoolConfig();
        GenericObjectPoolConfig.setMaxTotal(maxTotal);
        GenericObjectPoolConfig.setMaxIdle(maxIdle);
        GenericObjectPoolConfig.setMinIdle(minIdle);


        return JedisPoolingClientConfigurationBuilder.poolConfig(GenericObjectPoolConfig).build();
        // https://commons.apache.org/proper/commons-pool/apidocs/org/apache/commons/pool2/impl/GenericObjectPool.html
    }

    /*@Bean
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        //jedisPoolConfig.setMaxWaitMillis(m);
        return new JedisPool(jedisPoolConfig, host, port);


    }*/

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConFactory
                = new JedisConnectionFactory();
        jedisConFactory.setHostName("localhost");
        jedisConFactory.setPort(17402);
        jedisConFactory.setPassword("Redis123");
        jedisConFactory.setTimeout(0);
        return jedisConFactory;
    }

 /*   @Bean
    public JedisConnectionFactory getJedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        if (!StringUtils.isEmpty(password)) {
            redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        }
        redisStandaloneConfiguration.setPort(port);


        return new JedisConnectionFactory(redisStandaloneConfiguration, getJedisClientConfiguration());
    }
*/
    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(stringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.afterPropertiesSet();

//   	 redisTemplate.setKeySerializer(new StringRedisSerializer());
//   	 redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(new StringRedisSerializer()));
        return redisTemplate;
    }
}
