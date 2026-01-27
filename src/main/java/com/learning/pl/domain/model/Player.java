package com.learning.pl.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prem_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String playerName;
    private String nation;
    private String pos;
    private Integer age;
    private Integer matchesPlayed;
    private Integer starts;
    private Float minutesPlayed;
    private Float goals;
    private Float assists;
    private Float penaltiesScored;
    private Float yellowCards;
    private Float redCards;
    private Float expectedGoals;
    private Float expectedAssists;
    private String teamName;
}
