package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.config.TwilioConfiguration;
import br.com.pucminas.hospital.exceptions.BusinesException;
import br.com.pucminas.hospital.model.dto.SmsRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class SmsService {

    @Autowired
    private TwilioConfiguration twilioConfiguration;

    @Autowired
    private AssessmentService assessmentService;

    public void sendSms(SmsRequest smsRequest) {

        var phoneNumber = "+55" + smsRequest.getPhoneNumber();

        var to = new PhoneNumber(phoneNumber);
        var from = new PhoneNumber(twilioConfiguration.getTrialNumber());

        var message = smsRequest.getMessage();
        var messageCreator = Message.creator(to, from, message);

        messageCreator.create();
    }

//    public void dailyCallAssessment() {
//        var assessmentList = assessmentService.findDailyAssessment();
//
//        assessmentList.stream().forEach(a ->
//                sendSms(SmsRequest.builder()
//                        .phoneNumber(a.getPatient().getPhoneNumber())
//                        .message("Ainda precisa criar a mensagem")
//                        .build())
//        );
//    }

}