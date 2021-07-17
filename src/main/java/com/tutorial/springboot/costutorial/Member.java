package com.tutorial.springboot.costutorial;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Member {
    private int id;
    private String username;
    private String password;
    private String email;
}
