package com.turganov.studentservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "group-client", url = "http://localhost:4951")
@CrossOrigin
public interface GroupClient {

    @GetMapping("/groups")
    ResponseEntity<List<Group>> getAllGroups();

    @GetMapping("/groups/{id}")
    ResponseEntity<Group> getGroupsById(@PathVariable Long id);

}
