package com.vitalykhan.sweater.repository;

import com.vitalykhan.sweater.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {

}
