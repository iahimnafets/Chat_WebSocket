package com.chat.socket.service;

import com.chat.socket.dto.Message;
import com.chat.socket.entitys.Chat;
import com.chat.socket.exception.ApiRequestException;
import com.chat.socket.repository.ChatRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
@Data
public class MessagingService {

    private final ChatRepository chatRepository;

    public MessagingService(ChatRepository chatRepository){
        this.chatRepository = chatRepository;
    }

    public void saveMessage(Message message ){
     log.info( "saveMessage-RUN" );
        Chat chat =  Chat.builder()
                    .message(message.getText())
                    .Date(new Date() )
                    .from(message.getFrom())
                    .build();

        log.info(" params message : {}", message );
        try{
             chatRepository.save(chat);
        }catch(Exception ex ){
            throw new ApiRequestException("Failed to save message!");
        }
        log.info(" Record was inserted" );
    }

    public List<Chat> getAllMessages(){
        log.info( "getAllMessages-RUN" );
        List<Chat> result = null;
        try{
            result = chatRepository.findAll();
        }catch(Exception ex ){
            throw new ApiRequestException("Failed to retrieve all messages!");
        }
        return result;
    }

}