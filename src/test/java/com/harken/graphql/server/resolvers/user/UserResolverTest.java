package com.harken.graphql.server.resolvers.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserResolverTest {

	@Autowired
	private UserResolver userResolver;

	@Test
	void UserResolverTest() {
		assertNotNull(userResolver);
	}

}
