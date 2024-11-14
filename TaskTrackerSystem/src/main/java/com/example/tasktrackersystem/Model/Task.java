package com.example.tasktrackersystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Task {
    private int id;
    private String title;
    private String description;
    private String status;
}