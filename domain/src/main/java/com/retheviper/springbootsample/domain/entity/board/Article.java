package com.retheviper.springbootsample.domain.entity.board;

import com.retheviper.springbootsample.domain.entity.common.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Board article entity class.
 *
 * @author retheviper
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Article extends Auditable<String> {

    /**
     * Primary Key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Article's title
     */
    @Column(nullable = false)
    private String title;

    /**
     * Article's content
     */
    @Column(nullable = false)
    private String content;

    /**
     * Board that this article belongs
     */
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    /**
     * Category that this article belongs
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
