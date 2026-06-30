package com.option.poll.request;

import lombok.Data;

@Data
public class Vote {

     private Long pollId;
     private int optionIndex;

}
