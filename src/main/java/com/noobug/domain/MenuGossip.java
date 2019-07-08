package com.noobug.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Entity
@Table(name = "menu_gossip")
public class MenuGossip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long menu_id;

    Integer option_id;

    String content;

    String translation;

    Boolean needTranslate;
}
