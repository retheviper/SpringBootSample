package com.retheviper.springbootsample.domain.entity.member;

import com.retheviper.springbootsample.domain.entity.board.Board;
import com.retheviper.springbootsample.domain.entity.common.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

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
