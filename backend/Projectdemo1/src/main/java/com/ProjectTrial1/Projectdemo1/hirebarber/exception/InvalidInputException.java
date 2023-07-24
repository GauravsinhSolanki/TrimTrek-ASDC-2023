package com.ProjectTrial1.Projectdemo1.hirebarber.exception;

import com.ProjectTrial1.Projectdemo1.hirebarber.barbershift.BarberShiftService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(BarberShiftService.class);

    public InvalidInputException(String message) {
        super(message);
    }
}
