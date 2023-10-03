package com.turganov.student_management_system.service;

import com.turganov.student_management_system.entity.Group;

import java.util.List;

public interface GroupService {

    List<Group> getAllGroups();

    Group saveGroup(Group group);

    Group editGroup(Group group);

    boolean deleteGroup(Long id);

    Group findGroupById(Long id);
}
