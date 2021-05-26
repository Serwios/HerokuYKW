package com.dzeru.springloginformandoauth2tutorial.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "post")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String author;

    @NotNull
    private String paragraph;

    @NotNull
    @Column(length = 10000)
    private String content;

    @NotNull
    private String date;
}
