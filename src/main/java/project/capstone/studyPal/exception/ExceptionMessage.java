package project.capstone.studyPal.exception;

public enum ExceptionMessage {
    USER_WITH_ID_NOT_FOUND("user with id %d not found"),
    USER_WITH_EMAIL_NOT_FOUND("user with email %s not found"),
    INVALID_EMAIL("invalid email address"),

    ;
    private String message;


    ExceptionMessage(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
}
