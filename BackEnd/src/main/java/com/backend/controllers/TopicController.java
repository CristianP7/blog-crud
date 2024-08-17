package com.backend.controllers;

import com.backend.model.Topics;
import com.backend.service.impl.TopicsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topics")
public class TopicController {

    private final TopicsImpl topicService;

    @GetMapping("/searchTopics")
    public ResponseEntity<Page<Topics>> searchTopics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "40") int size) throws Exception {

        Pageable pageable = PageRequest.of(page, size);
        Page<Topics> topicPage = topicService.readAll(pageable);
        return new ResponseEntity<>(topicPage, HttpStatus.OK);
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

    @PostMapping("/create")
    public ResponseEntity<Topics> createTopic(@RequestBody Topics topic) {
        try {
            Topics createdTopic = topicService.save(topic);
            return new ResponseEntity<>(createdTopic, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Topics> updateTopic(@PathVariable int id, @RequestBody Topics topic) {
        try {
            Topics updatedTopic = topicService.update(topic, id);
            return new ResponseEntity<>(updatedTopic, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
