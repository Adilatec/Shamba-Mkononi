package tz.go.ega.shambamkononibackend.util;

public class ResponseCode {

    public static final int RECORD_EXIST = 5009;
    public static int SUCCESS = 5000;
    public static int DOES_NOT_EXIST = 5001;
    public static int EMAIL_ALREADY_EXIST = 5002;
    public static int PHONE_ALREADY_EXIST = 5003;
    public static int NULL_ARGUMENT = 5004;
    public static int FAIL = 5005;
    public static int UNAUTHORIZED = 5006;
    public static int OPERATION_NOT_ALLOWED = 5007;
    public static int PASSWORD_DO_NO_MATCH = 5008;
    public static int PASSWORD_DO_NOT_MATCH = 5009;
    public static int WEAK_PASSWORD = 5010;
    public static int TIN_ALREADY_EXIST = 5011;
    public static final String MSG_DOES_NOT_EXIST = "Record not found";
    public static final String MSG_DOES_EXIST = "Record found";
    public static final String MSG_OPERATION_SUCCESSFUL = "Operation successful";
    public static final String MSG_OPERATION_UNSUCCESSFUL = "Operation failed";
    public static final String MSG_INTERNAL_SERVER_ERROR = "Internal server error";
    public static final String MSG_UNAUTHORIZED = "Unauthorized";
    public static final String tokenType = "Bearer";

}
