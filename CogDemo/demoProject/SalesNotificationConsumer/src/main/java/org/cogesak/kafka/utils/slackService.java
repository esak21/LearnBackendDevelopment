package org.cogesak.kafka.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;

import java.io.IOException;

@Service
@Slf4j
public class slackService {
    @Value("${slack.webhook}")
    private String urlSlackWebHook;
    private static final String NEW_LINE = "\n";

    public void sendMessageToSlack(String message) {
        StringBuilder messageBuider = new StringBuilder();
        messageBuider.append("*My message*: " + message + NEW_LINE);

        process(messageBuider.toString());
    }

    private void process(String message) {
        Payload payload = Payload.builder()
                .channel("#projectbooks")
                .username("Books Bot")
                .iconEmoji(":rocket:")
                .text(message)
                .build();
        try {
            WebhookResponse webhookResponse = Slack.getInstance().send(
                    urlSlackWebHook, payload);
            log.info("code -> " + webhookResponse.getCode());
            log.info("body -> " + webhookResponse.getBody());
        } catch (IOException e) {
            log.error("Unexpected Error! WebHook:" + urlSlackWebHook);
        }
    }

    private String exampleMessage(){
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                + "Aliquam eu odio est. Donec viverra hendrerit lacus et tempor.";
    }

}
