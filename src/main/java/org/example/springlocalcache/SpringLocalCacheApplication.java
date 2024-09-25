package org.example.springlocalcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching // local cache를 사용하기 위함
public class SpringLocalCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLocalCacheApplication.class, args);
	}

}
