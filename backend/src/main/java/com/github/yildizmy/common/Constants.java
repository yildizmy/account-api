package com.github.yildizmy.common;

/**
 * Constant variables used in the project
 */
public final class Constants {

    private Constants() {
    }

    public static final String TRACE = "trace";
    public static final String SUCCESS = "Success";
    public static final String TRANSACTION_DESCRIPTION = "Transaction to %s %s with the amount of %.2f";
    public static final String VALIDATION_ERROR = "Validation error. Check 'errors' field for details";
    public static final String METHOD_ARGUMENT_NOT_VALID = "MethodArgumentNotValid exception";
    public static final String NOT_FOUND = "Requested element is not found";
    public static final String NOT_FOUND_RECORD = "Not found any record";
    public static final String EMAIL_NOT_VALID = "e-mail is not valid";
    public static final String NOT_FOUND_CUSTOMER = "Requested customer is not found";
    public static final String ALREADY_EXISTS = "Requested element already exists";
    public static final String ALREADY_EXISTS_CUSTOMER = "Customer with the same email already exists";
    public static final String TRANSACTION_ADDED = "Transaction is added to the account";
    public static final String INITIAL_AMOUNT_EQUAL_OR_GREATER_ZERO = "Initial amount must be equal to or greater than zero";
}
