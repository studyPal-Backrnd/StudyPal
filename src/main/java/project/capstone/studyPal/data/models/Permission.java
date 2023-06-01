package project.capstone.studyPal.data.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
        ADMIN_READ("admin:read"),
        ADMIN_UPDATE("admin:update"),
        ADMIN_CREATE("admin:create"),
        ADMIN_DELETE("admin:delete"),
        STUDENT_READ("management:read"),
        STUDENT_UPDATE("management:update"),
        STUDENT_CREATE("management:create"),
        STUDENT_DELETE("management:delete");

        @Getter
        private final String permission;

}
