package logic;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMS {
    private static final String ACCOUNT_SID = "ACf2d96e47b33c2bf429443a246c9a10d4";
    private static final String AUTH_TOKEN = "34784f9d93f1d13ee8982d33f170c763";
    private String msgBody;
    private Person phoneNumber;

    public SMS(String msgBody, Person toPerson){
        this.msgBody = msgBody;
        this.phoneNumber = toPerson;
    }

    public static void sendMessage(String toPerson, String msgBody ){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(
                new com.twilio.type.PhoneNumber(toPerson),
                new com.twilio.type.PhoneNumber("+12027598758"),
                msgBody)
                .create();
    }
}