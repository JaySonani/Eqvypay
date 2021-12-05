package com.eqvypay.service.repository;

import org.springframework.stereotype.Repository;

import com.eqvypay.persistence.User;

@Repository
public interface FriendRepository {

    public void addFriendByEmail(User user, String email) throws Exception;

    public void addFriendByContact(User user, String contact) throws Exception;

    public void removeFriendByEmail(User user, String email) throws Exception;

    public void removeFriendByContact(User user, String contact) throws Exception;
}
