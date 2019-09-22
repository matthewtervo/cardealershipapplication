package com.itrelliscardealership.web.util;

public enum SearchOperation {
    EQUALITY, NEGATION, GREATER_THAN, LESS_THAN;

    public static final String[] SIMPLE_OPERATION_SET = { ":", "!", ">", "<"};

    public static SearchOperation getSimpleOperation(char input) {
        switch (input) {
            case ':':
                return EQUALITY;
            case '!':
                return NEGATION;
            case '>':
                return GREATER_THAN;
            case '<':
                return LESS_THAN;
            default:
                return null;
        }
    }
}
