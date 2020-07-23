package com.retheviper.springbootsample.domain.entity.member;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

import com.retheviper.springbootsample.domain.entity.board.Board;
import com.retheviper.springbootsample.domain.entity.common.Auditable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Member information entity class.
 *
 * @author retheviper
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class MemberInformation extends Auditable<String> {

    /**
     * Primary Key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Member's E-mail
     */
    @Email
    @Column(unique = true)
    private String email;

    /**
     * Member that this member information belongs
     */
    @OneToOne(mappedBy = "memberInformation")
    private Member member;

    /**
     * Member's favorite board
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_information_id")
    private List<Board> boards;
}
