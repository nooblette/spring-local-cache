package org.example.springlocalcache.user;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	@Cacheable(value = "userCache", key = "#id")
	public User getUserName(Long id) {
		log.info("getUserName: {}", id);
		return userRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("대상 유저를 찾을 수 없습니다."));
	}
}