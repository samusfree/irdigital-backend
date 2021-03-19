package com.samus.irdigital.backend.exceptions;

public class AgeAndBornDateDoesntCorrespondException extends RuntimeException {
    public AgeAndBornDateDoesntCorrespondException() {
        super("The age and the born date doesn't correspond");
    }
}
