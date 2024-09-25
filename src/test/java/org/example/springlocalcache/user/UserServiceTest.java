package org.example.springlocalcache.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserServiceTest {
	@SpyBean // userRepository를 스파이로 주입
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private CacheManager cacheManager;

	@BeforeEach
	void setUp() {
		userRepository.save(new User(1L, "테스터", 20));
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void getUserName() {
		// 첫 번째 호출 (실제 메서드 실행)
		User userNonCache = userService.getUserName(1L);

		// 캐시 상태 확인
		Cache userCache = cacheManager.getCache("userCache");
		if(userNonCache != null) {
			System.out.println("cached value - " + 1L + " : " +  userCache.get(1L).get());
		}

		// 두 번째 호출 (캐시에서 가져와야 하므로 실제 메서드 실행이 발생하지 않음)
		User cachedUser = userService.getUserName(1L);

		// UserRepository의 findById 메서드가 한 번만 호출되었는지 검증
		verify(userRepository, times(1)).findById(1L);
		assertEquals(cachedUser, userNonCache);
	}
}