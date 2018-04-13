package connect.database.test.com.clents;

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
    /* -*- SignUp -* -*/
    SU_SUCCESS("SignUp Succcess"),
    SU_FAIL("SignUp Fail"),
    SU_NOTMATCH("SignUp NotMatch"),
    /* -*- System Error -*- */
    SYS_NETERR("System NetErr"),
    SYS_MAINTAIN("System Maintain");

    MessageBox(String message){

    }
}
