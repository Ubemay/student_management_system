package com.turganov.groupservice;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final StudentClient studentClient;

    public GroupServiceImpl(GroupRepository groupRepository, StudentClient studentClient) {
        this.groupRepository = groupRepository;
        this.studentClient = studentClient;
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group editGroup(Group updatedGroup) {
        Long id = updatedGroup.getId();

        // Retrieve the list of students from the student-service
        ResponseEntity<List<Student>> studentResponse = studentClient.getStudentByGroup(id);
        List<Student> students = studentResponse.getBody();

        // Log retrieved students for debugging
        System.out.println("Retrieved students: " + students);

        Group existingGroup = groupRepository.findById(id).orElse(null);

        if (existingGroup != null) {
            // Update the Group entity with the new data (excluding students)
            existingGroup.setGroupName(updatedGroup.getGroupName());
            existingGroup.setFaculty(updatedGroup.getFaculty());

            // You might want to perform additional logic with students retrieved from student-service
            // For example, you can associate students with the group by setting their group IDs

            // Save the updated Group entity
            return groupRepository.save(existingGroup);
        } else {
            return null;
        }
    }


    @Override
    public boolean deleteGroup(Long id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Group findGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }
}
