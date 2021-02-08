package com.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Book does not belong to current user")
public class BookDoesNotBelongToUserException extends RuntimeException{
}
