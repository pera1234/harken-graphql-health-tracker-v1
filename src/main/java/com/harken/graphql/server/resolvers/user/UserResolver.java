package com.harken.graphql.server.resolvers.user;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.harken.graphql.server.domain.user.User;
import com.harken.graphql.server.repositories.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class UserResolver implements GraphQLQueryResolver {

    private UserRepository userRepository;

    public User userById(ObjectId id) {
        return userRepository.findById(id);
    }

    public User userByEmail(String email) {
        return userRepository.findOne(email);
    }

    public List<User> users() {
        return userRepository.findAll(User.class);
    }
}
