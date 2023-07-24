package com.ProjectTrial1.Projectdemo1.hirebarber.exception;

import com.ProjectTrial1.Projectdemo1.hirebarber.slotbooking.BookedSlotsServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class BookedSlotException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(BookedSlotsServices.class);

    public BookedSlotException(String message) {
        super(message);
    }
}
