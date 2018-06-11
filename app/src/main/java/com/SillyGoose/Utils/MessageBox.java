package com.SillyGoose.Utils;

/**
 * match to server
 * Created by wangj on 4/3/2018.
 */

public enum MessageBox {
    /* -*- SignIn -*- */
    SI_SUCCESS("SignIn Success"),
    SI_FAIL("SignIn Fail"),
    SI_MISS("Sign Miss"),
    SI_NOTFIND("SignIn NotFind"),
    SI_PASSWORDWRONG("SignIn Password Wrong"),
    SI_ANOTHERPLACE("SignIn AnotherPlace"),
    SI_ALREADYSIGNIN("Already SignIn"),
    /* -*- SignUp -* -*/
    SU_SUCCESS("SignUp Succcess"),
    SU_FAIL("SignUp Fail"),
    SU_NOTMATCH("SignUp NotMatch"),
    SU_EXISTS("SignUp User Already Exists"),
    /* -*- System Error -*- */
    SYS_NETERR("System NetErr"),
    SYS_MAINTAIN("System Maintain"),
    SYS_ERROR("System error"),
    UG_SUCCESS("Update Goose Success"),
    UC_SUCCESS("Update CollectTime Success"),
    ;
    MessageBox(String message){

    }
}
