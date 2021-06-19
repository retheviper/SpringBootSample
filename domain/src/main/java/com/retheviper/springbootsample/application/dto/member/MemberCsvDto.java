package com.retheviper.springbootsample.application.dto.member;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Member DTO class.
 *
 * @author retheviper
 */
@Data
public class MemberCsvDto implements Serializable {

    /**
     * Member ID
     */
    @CsvBindByName
    private long id;

    /**
     * Member's user ID
     */
    @CsvBindByName
    private String userId;

    /**
     * Member's name
     */
    @CsvBindByName
    private String name;
}
