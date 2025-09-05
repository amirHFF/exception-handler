package io.github.amirHFF.errorCode;

/**
 * 1 not found
 * 2 bad input
 * 3 duplicate
 * 4 business
 * 5 jsonException
 * 6 jsonException
 * 7 podException
 */
public interface ErrorCode {

    String getCode();

    public String getMessage();

}
