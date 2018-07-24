package ua.training.servlets;

public interface RegexContainer {
    String LOGIN_REGEX = "^[A-Za-z0-9_-]{5,15}$";
    String FIRST_NAME_REGEX = "^[A-Z][a-z]+$";
    String EMAIL_REGEX = "^[A-Za-z0-9_-]{3,15}@[a-z]{3,10}\\.[a-z]{2,3}$";
}
