package com.turganov.groupservice;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@RestController
@CrossOrigin
public class GroupController {

    private final GroupService groupService;
    private final RestTemplate restTemplate;
    private final StudentClient studentClient;


    public GroupController(GroupService groupService, RestTemplate restTemplate, StudentClient studentClient) {
        this.groupService = groupService;
        this.restTemplate = restTemplate;
        this.studentClient = studentClient;
    }

    @GetMapping("/groups")
    public ResponseEntity<List<Group>> listGroups() {
        List<Group> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/groups")
    public ResponseEntity<String> saveGroup(@RequestBody Group group) {
        try {
            groupService.saveGroup(group);
            return new ResponseEntity<>("Группа успешно сохранена", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/groups/{id}")
    public ResponseEntity<String> editGroup(@PathVariable Long id, @RequestBody Group updatedGroup) {
        try {
            Group editedGroup = groupService.editGroup(updatedGroup);

            if (editedGroup != null) {
                return new ResponseEntity<>("Группа успешно обновлена", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Группа не найдена", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable Long id) {
        try {
            boolean deleted = groupService.deleteGroup(id);

            if (deleted) {
                return new ResponseEntity<>("Группа успешно удалена", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Группа не найдена", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/groups/{id}")
    public ResponseEntity<Group> findGroupById(@PathVariable Long id) {
        Group group = groupService.findGroupById(id);

        if (group != null) {
            return new ResponseEntity<>(group, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/groups/{groupId}/students")
    public ResponseEntity<?> getStudentsByGroupId(@PathVariable Long groupId) {
        return studentClient.getStudentByGroup(groupId);
    }

}
