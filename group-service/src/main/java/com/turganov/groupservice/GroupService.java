package com.turganov.groupservice;

import java.util.List;

public interface GroupService {

    List<Group> getAllGroups();

    Group saveGroup(Group group);

    Group editGroup(Group group);

    boolean deleteGroup(Long id);

    Group findGroupById(Long id);

}
