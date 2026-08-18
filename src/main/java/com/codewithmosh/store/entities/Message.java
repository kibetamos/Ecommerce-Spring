package com.codewithmosh.store.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor  // ← ADD THIS
@Getter
public class Message {
    private String text;
}
