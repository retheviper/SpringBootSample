package com.retheviper.springbootsample.domain.entity.board;

import com.retheviper.springbootsample.domain.entity.common.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Article category entity class.
 *
 * @author retheviper
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Category extends Auditable<String> {

    /**
     * Primary Key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Category's name
     */
    @Column(unique = true, nullable = false)
    private String name;

    /**
     * Board that this category belongs
     */
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
