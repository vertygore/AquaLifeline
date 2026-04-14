package com.AquaLifeLine.Backend;

public interface UserRepository extends org.springframework.data.jpa.repository.JpaRepository<User, Long> {
    public User findByName(String name);
}
