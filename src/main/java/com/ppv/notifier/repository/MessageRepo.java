package com.ppv.notifier.repository;

import com.ppv.notifier.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Pavlo.Pavlichenko
 */
@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

    List<Message> findAllByAuthorId(String id);
}
