package com.eqvypay.service.groups;

import com.eqvypay.persistence.Group;
import com.eqvypay.persistence.User;

import java.util.ArrayList;
import java.util.List;

public interface IGroupDataManipulation {
    public void createTable() throws Exception;

    public void createGroupMembersTable() throws Exception;

    public boolean tableExist(String tableName) throws Exception;

    public List<Group> getAllGroups() throws Exception;

    public List<String> getFriendsGroupIds(User user) throws Exception;

	ArrayList<Group> getAllJoinedGroups(User user) throws Exception;
}
