package com.chat.socket.controller;


import com.chat.socket.dto.Message;
import com.chat.socket.dto.OutputMessage;
import com.chat.socket.dto.Response;
import com.chat.socket.service.MessagingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;


@RestController
@Slf4j
public class ChatController
{

    private final WebSocketMessageBrokerStats webSocketMessageBrokerStats;
    private final BuildProperties buildProperties;
    private final MessagingService messagingService;

    public ChatController(MessagingService messagingService,
                          WebSocketMessageBrokerStats webSocketMessageBrokerStats,
                          BuildProperties buildProperties ){
        this.messagingService = messagingService;
        this.webSocketMessageBrokerStats = webSocketMessageBrokerStats;
        this.buildProperties = buildProperties;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(Message message) throws Exception {

        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        messagingService.saveMessage( message );
        return new OutputMessage(message.getFrom(), message.getText(), time);
    }

    @Operation( summary =  "Get all messages regarding a conversation" )
    @GetMapping( value = "/messages" )
    public ResponseEntity<Response> getAllMessages() {

       return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .data(Map.of("messages", messagingService.getAllMessages( ) ))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @Operation( summary =  "return the status for this application" )
    @GetMapping( value = "/status-application" )
    public ResponseEntity<Response> getStatusApplication() {

        log.info("getWebSocketSessionStatsInfo ->  " + webSocketMessageBrokerStats.getWebSocketSessionStatsInfo());

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .data(Map.of("status-application", webSocketMessageBrokerStats.getWebSocketSessionStatsInfo() ))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @Operation( summary =  "return the name and version for this application" )
    @GetMapping( value = "/version-application" )
    public ResponseEntity<Response> getVersionApplication() {

        String nameAndVersionApp = buildProperties.getName() + "-" + buildProperties.getVersion();
        log.info("version-application: {} ", nameAndVersionApp);

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .data(Map.of("version-application", nameAndVersionApp ))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

}
