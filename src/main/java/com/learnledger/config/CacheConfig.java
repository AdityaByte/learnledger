package com.learnledger.config;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
    
    @Bean
    public CacheManager cacheManager(){
        return new ConcurrentMapCacheManager("card");
    }
    
    @Bean
    public net.sf.ehcache.CacheManager ehCacheFactory(){
        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        config.addCache(new CacheConfiguration("card" , 1000).timeToLiveSeconds(600));
        return net.sf.ehcache.CacheManager.newInstance(config);
    }
    
}
