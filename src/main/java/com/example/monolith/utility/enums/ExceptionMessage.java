package com.example.monolith.utility.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum ExceptionMessage {
    NOT_ASSIGNED ("STUDENT NOT YET ASSIGNED TO COURSE OR STUDENT DOESN'T EXIST."),
    NOT_EXIST ("OBJECT NOT EXIST INTO THE DATABASE"),
    EXIST ("OBJECT ALREADY EXIST INTO THE DATABASE"),
    INVALID_GRADE ("GRADES TO BE BETWEEN 2 AND 6."),
    EMPTY ("INTERNAL SERVER DATABASE IS EMPTY OR CORRUPTED"),
    NON_ENROLL ("STUDENT/COURSE NON-EXISTENT OR NOT ASSIGNED"),
    NO_GRADES ("STUDENT HAVE NO GRADES OR DATA IS CORRUPTED"),
    NOT_REGISTERED ("USER NOT FOUND PLEASE REGISTER"),
    ALREADY_REGISTERED ("ALREADY IS REGISTERED ON THE SYSTEM");

    private final String ExceptionMessage;

}
