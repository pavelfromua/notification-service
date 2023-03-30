package com.ppv.notifier.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ppv.notifier.enums.MessageType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Class which describes the structure of messages in db.
 *
 * @author Pavlo.Pavlichenko
 */
@Entity
@Table
@Data
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Enumerated(EnumType.STRING)
	private MessageType messageType;

	@Column(name = "contact")
	private String recipientContact;

	@Column(name = "name")
	private String recipientName;

	private String subject;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime sentBy;
	@ManyToOne
	private User author;
}
