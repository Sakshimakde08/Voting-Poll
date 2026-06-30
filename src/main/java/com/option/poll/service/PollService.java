package com.option.poll.service;

import com.option.poll.model.OptionVote;
import com.option.poll.model.Poll;
import com.option.poll.repository.PollRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PollService {

    private final PollRepo pollRepo;

    public Poll addPoll(@RequestBody Poll poll) {
        return pollRepo.save(poll);
    }

    public List<Poll> findAllPoll() {
        return pollRepo.findAll();
    }

    public Optional<Poll> getPollById(Long id) {
        return pollRepo.findById(id);
    }


    public void vote(Long pollId, int optionIndex) {

        Poll poll = pollRepo.findById(pollId).orElseThrow(() -> new RuntimeException("Poll Not Found"));

        List<OptionVote> options = poll.getOptions();

        if (optionIndex < 0 || optionIndex >= options.size()) {
            throw new IllegalArgumentException("Option Not Valid");
        }

        OptionVote selectedOption = options.get(optionIndex);

        selectedOption.setVoteCount(selectedOption.getVoteCount() +1);

        pollRepo.save(poll);
    }
}
