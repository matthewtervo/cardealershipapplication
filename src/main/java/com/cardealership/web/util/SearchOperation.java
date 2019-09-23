package com.cardealership.web.util;

public enum SearchOperation {
    EQUALITY, GREATER_THAN, LESS_THAN;

    public static final String[] SIMPLE_OPERATION_SET = { ":", ">", "<"};

    public static SearchOperation getSimpleOperation(char input) {
        switch (input) {
            case ':':
                return EQUALITY;
            case '>':
                return GREATER_THAN;
            case '<':
                return LESS_THAN;
            default:
                return null;
        }
    }
}
