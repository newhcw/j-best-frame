package com.j.best.common.cache.config;

import io.lettuce.core.TimeoutOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.resource.ClientResources;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

import static io.lettuce.core.ReadFrom.REPLICA_PREFERRED;


@Configuration
public class RedisConfiguration {


    /**
     *
     * 解决方案1：
     *
     * 升级到SpringBoot2.3.0或以上版本。并添加如下配置项
     * spring.redis.timeout=60s
     * spring.redis.lettuce.cluster.refresh.period=60s
     * spring.redis.lettuce.cluster.refresh.adaptive=true
     * 解决方案2：
     * 配置LettuceConnectionFactory ，设置拓扑刷新策略。
     *
     *
     * @param redisProperties
     * @param clientResources
     * @return
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory(RedisProperties redisProperties, ClientResources clientResources) {

        // 写master，读从
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .readFrom(REPLICA_PREFERRED)
                .build();

        ClusterTopologyRefreshOptions topologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
                .enablePeriodicRefresh(Duration.ofSeconds(30)) //按照周期刷新拓扑
                .enableAllAdaptiveRefreshTriggers() //根据事件刷新拓扑
                .build();

        ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder()
                //redis命令超时时间,超时后才会使用新的拓扑信息重新建立连接
                .timeoutOptions(TimeoutOptions.enabled(Duration.ofSeconds(10)))
                .topologyRefreshOptions(topologyRefreshOptions)
                .build();

        LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder()
                .clientResources(clientResources)
                .clientOptions(clusterClientOptions)
                .build();

        RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration(redisProperties.getCluster().getNodes());
        clusterConfig.setMaxRedirects(redisProperties.getCluster().getMaxRedirects());
        clusterConfig.setPassword(RedisPassword.of(redisProperties.getPassword()));

        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(clusterConfig, clientConfiguration);

        return lettuceConnectionFactory;
    }


    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();

        /* 设置value的序列化规则和 key的序列化规则 */
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        redisTemplate.setKeySerializer(stringRedisSerializer);                // key采用String的序列化方式
        redisTemplate.setHashKeySerializer(stringRedisSerializer);            // hash的key也采用String的序列化方式
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer); // value序列化方式采用jackson
        redisTemplate.setConnectionFactory(connectionFactory);                // 默认使用letttuce，如果想使用Jedis，创建JedisConnectionFactory实例作为参数传入

        return redisTemplate;
    }

}
