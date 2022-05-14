package com.taisiia.shop.config;

import com.hazelcast.config.*;
import com.taisiia.shop.domain.dao.Category;
import com.taisiia.shop.domain.dao.Producer;
import com.taisiia.shop.domain.dao.Product;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class HazelcastConfig {

    @Bean
    Config configHazelcast() {
        var config = new Config()
                .setInstanceName("instance-1")
                .addMapConfig(new MapConfig()
                        .setName("products")
                        .setEvictionConfig(new EvictionConfig()
                                .setEvictionPolicy(EvictionPolicy.LFU)
                                .setSize(10)
                                .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE))
                        .setTimeToLiveSeconds(60 * 60));
        config.getSerializationConfig().addDataSerializableFactory(1, id -> id == 1 ? new Product() : null)
                .addDataSerializableFactory(2, id -> id == 2 ? new Producer() : null)
                .addDataSerializableFactory(3, id -> id == 3 ? new Category() : null);
        return config;
    }
}
