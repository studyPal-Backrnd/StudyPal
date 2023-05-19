package project.capstone.studyPal.config.app;


import lombok.Getter;

@Getter
public class MailConfig {
    private final String hostMailName;
    private final String hostMailAddress;
    public MailConfig(String hostMailName, String hostMailAddress) {
        this.hostMailName = hostMailName;
        this.hostMailAddress = hostMailAddress;
    }
}
