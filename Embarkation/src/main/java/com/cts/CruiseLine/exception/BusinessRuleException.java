package com.cts.CruiseLine.exception;

/**
 * Thrown when a domain/business invariant is violated
 * (e.g. booking a sold-out cabin, exceeding credit limit).
 */
public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String message) {
        super(message);
    }
}
