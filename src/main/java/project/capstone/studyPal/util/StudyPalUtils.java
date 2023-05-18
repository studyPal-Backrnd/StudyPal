package project.capstone.studyPal.util;

public class StudyPalUtils {
    public static final String EMAIL_REGEX_STRING="^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    public static  final String ACCOUNT_VERIFICATION_MAIL_TEMPLATE_LOCATION="/home/conerstonez/IdeaProjects/studyPal/src/main/resources/verification-mail.html";
    public static  final String PASSWORD_RESET_MAIL_TEMPLATE_LOCATION="/home/conerstonez/IdeaProjects/studyPal/src/main/resources/reset-password-mail.html";
    public static final String PASSWORD_REGEX_STRING = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    public static final String NAME_REGEX = "^[a-zA-Z\\s]+$";

}
