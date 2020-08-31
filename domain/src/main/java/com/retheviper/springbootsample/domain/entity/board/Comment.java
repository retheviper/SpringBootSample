package com.retheviper.springbootsample.domain.entity.board;

import com.retheviper.springbootsample.domain.entity.common.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Article Comment entity class.
 *
 * @author retheviper
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Comment extends Auditable<String> {

    /**
     * Primary Key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Comment's content
     */
    @Column(nullable = false)
    private String content;

    /**
     * Article that this comment belongs
     */
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
}
