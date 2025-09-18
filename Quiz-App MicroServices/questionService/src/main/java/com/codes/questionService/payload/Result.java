package com.codes.questionService.payload;

import com.codes.questionService.model.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private boolean success;
    private String message;
    private List<Question> questions;
}
