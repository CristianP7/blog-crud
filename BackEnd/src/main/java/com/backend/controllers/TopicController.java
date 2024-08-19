package com.backend.controllers;

import com.backend.model.Topics;
import com.backend.model.dto.TopicDTO;
import com.backend.service.impl.TopicsImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog/topics")
public class TopicController {

    private final TopicsImpl topicService;

    private final ModelMapper mapper;

    @GetMapping("/searchTopics")
    public ResponseEntity<Page<TopicDTO>> searchTopics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "40") int size) throws Exception {

        Pageable pageable = PageRequest.of(page, size);
        Page<Topics> topicPage = topicService.readAll(pageable);
        Page<TopicDTO> topicDTOS = topicPage.map(this::convertToDto);
        return new ResponseEntity<>(topicDTOS, HttpStatus.OK);
    }

    private TopicDTO convertToDto(Topics topic) {
        return mapper.map(topic, TopicDTO.class);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Topics> getTopicById(@PathVariable int id) {
        try {
            Topics topic = topicService.readById(id);
            return new ResponseEntity<>(topic, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Topics> createTopic(@RequestBody Topics topic) {
        try {
            Topics createdTopic = topicService.save(topic);
            return new ResponseEntity<>(createdTopic, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Topics> updateTopic(@PathVariable int id, @RequestBody Topics topic) {
        try {
            Topics updatedTopic = topicService.update(topic, id);
            return new ResponseEntity<>(updatedTopic, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable int id) {
        try {
            topicService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
