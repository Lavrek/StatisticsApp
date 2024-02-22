package com.vodahory.statistics.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The {@code GoodsNotFoundException} class represents an exception that is thrown when a request to retrieve goods
 * results in a "Gone" HTTP status (HTTP 410). This exception typically indicates that the requested goods or resource
 * is no longer available.
 *
 * <p>
 * This exception is annotated with {@link org.springframework.web.bind.annotation.ResponseStatus} to specify the HTTP
 * status code to be returned when this exception is thrown, which in this case is {@link org.springframework.http.HttpStatus#GONE}.
 * </p>
 *
 * <p>
 * This exception is a subtype of {@link java.lang.RuntimeException}, allowing it to be unchecked and thrown in response
 * to specific business logic conditions.
 * </p>
 *
 * @author Ing. Ekaterina Lavrova
 * @version 1.0
 * @since 2023-09-05
 */
@ResponseStatus(value = HttpStatus.GONE)
public class GoodsNotFoundException extends RuntimeException {
}
