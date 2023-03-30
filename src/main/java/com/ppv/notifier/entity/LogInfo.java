package com.ppv.notifier.entity;

import com.ppv.notifier.enums.LogType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Pavlo.Pavlichenko
 */
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private LogType type;

    private String message;

}
