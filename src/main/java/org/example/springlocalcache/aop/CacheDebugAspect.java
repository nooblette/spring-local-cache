package org.example.springlocalcache.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheDebugAspect {

	@Before("execution(* org.example.springlocalcache.user.UserService.getUserName(..))")
	public void logBeforeService(JoinPoint joinPoint) {
		System.out.println("Before executing service method: " + joinPoint.getSignature());
	}

	@Before("execution(* org.example.springlocalcache.user.UserRepository.findById(..))")
	public void logBeforeRepository(JoinPoint joinPoint) {
		System.out.println("Before executing repository method: " + joinPoint.getSignature());
	}
}
