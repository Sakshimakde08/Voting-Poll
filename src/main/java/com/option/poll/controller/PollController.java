package com.option.poll.controller;
import com.option.poll.model.Poll;
import com.option.poll.request.Vote;
import com.option.poll.service.PollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/poll")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class PollController {

    private final PollService pollService ;

    @PostMapping
    public Poll createPoll(@RequestBody Poll poll){
        return pollService.addPoll(poll);
    }

    @GetMapping
    public List<Poll> getAllPoll(){
        return pollService.findAllPoll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long id){
        return pollService.getPollById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/vote")
    public void vote(@RequestBody Vote vote){
        pollService.vote(vote.getPollId(),vote.getOptionIndex());
    }
}
