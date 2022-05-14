package com.taisiia.shop.service.impl

import com.taisiia.shop.domain.dao.Role
import com.taisiia.shop.domain.dao.User
import com.taisiia.shop.repository.RoleRepository
import com.taisiia.shop.repository.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

import javax.persistence.EntityNotFoundException

class UserServiceImplSpec extends Specification {
    def userRepository = Mock(UserRepository)
    def roleRepository = Mock(RoleRepository)
    def passwordEncoder = Mock(PasswordEncoder)
    def userServiceImpl = new UserServiceImpl(userRepository, roleRepository, passwordEncoder)

    def "should get user by id"() {
        given:
        def id = 5

        when:
        userServiceImpl.findById(id)

        then:
        1 * userRepository.getById(id)
        0 * _
    }

    def "should delete user by id"() {
        given:
        def id = 5

        when:
        userServiceImpl.delete(id)

        then:
        1 * userRepository.deleteById(id)
        0 * _
    }

    def "should get user page"() {
        given:
        def pageRequest = PageRequest.of(2, 2)

        when:
        userServiceImpl.getPage(pageRequest)

        then:
        1 * userRepository.findAll(pageRequest)
        0 * _
    }

    def "should save user"() {
        given:
        def user = Mock(User)
        def role = new Role(name: "ROLE_USER")
        def optionalRole = Optional.of(role)

        when:
        userServiceImpl.save(user)

        then:
        1 * roleRepository.findByName("ROLE_USER") >> optionalRole
        1 * user.setRoles(Set.of(role))
        1 * user.getPassword() >> "password"
        1 * passwordEncoder.encode("password") >> "encode_password"
        1 * user.setPassword("encode_password")
        1 * userRepository.save(user)
        0 * _

    }

    def "should update user"() {
        given:
        def id = 5;
        def user = new User(firstName: "Anna", lastName: "Tkaczyk", email: "ania@gmail.com")
        def userById = Mock(User)


        when:
        userServiceImpl.update(user, id)

        then:
        1 * userRepository.getById(5) >> userById
        1 * userById.setEmail(user.getEmail())
        1 * userById.setFirstName(user.getFirstName())
        1 * userById.setLastName(user.getLastName())
        0 * _
    }

    def "should not find user by email"() {
        given:
        def email = "ania@gmail.com"
        def optionalUser = Optional.empty()
        userRepository.findByEmail(email) >> optionalUser

        when:
        userServiceImpl.findUserByEmail(email)

        then:
        thrown EntityNotFoundException

    }

    def "should find user by email"() {
        given:
        def email = "ania@gmail.com"
        def optionalUser = Optional.of(new User())


        when:
        userServiceImpl.findUserByEmail(email)

        then:
        1 * userRepository.findByEmail(email) >> optionalUser
        0 * _

    }

    def "should find current user"() {
        given:
        def securityContext = Mock(SecurityContext)
        SecurityContextHolder.setContext(securityContext)
        def authentication = Mock(Authentication)
        def name = "ania@gmail.com"
        def optionalUser = Optional.of(new User())

        when:
        userServiceImpl.getCurrentUser()

        then:
        1 * securityContext.getAuthentication() >> authentication
        1 * authentication.getName() >> name
        1 * userRepository.findByEmail(name) >> optionalUser
        0 * _
    }

    def "should not find current user"(){
        given:
        def securityContext = Mock(SecurityContext)
        SecurityContextHolder.setContext(securityContext)
        def authentication = Mock(Authentication)
        def name = "ania@gmail.com"
        def optionalUser = Optional.empty()
        securityContext.getAuthentication() >> authentication
        authentication.getName() >> name
        userRepository.findByEmail(name) >> optionalUser


        when:
        userServiceImpl.getCurrentUser()

        then:
        thrown EntityNotFoundException
    }
}
