package controller;

import com.chat.socket.controller.ChatController;
import com.chat.socket.dto.Response;
import com.chat.socket.entitys.Chat;
import com.chat.socket.repository.ChatRepository;
import com.chat.socket.service.MessagingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChatControllerTest {


    private ChatController chatController;
    private WebSocketMessageBrokerStats webSocketMessageBrokerStats;
    private BuildProperties buildProperties;


    private MessagingService messagingService;
    private ChatRepository chatRepository;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        chatRepository = mock(ChatRepository.class);
        messagingService = new MessagingService(chatRepository);
        webSocketMessageBrokerStats  = mock(WebSocketMessageBrokerStats.class);
        buildProperties  = mock(BuildProperties.class);
        chatController = new ChatController(messagingService, webSocketMessageBrokerStats, buildProperties );
    }




    @Test
    public void getAllMessages()
    {
        Chat chat1= new Chat(Long.parseLong("1"), "Jack", "Hi...", new Date());
        Chat chat2 = new Chat(Long.parseLong("2") , "Alex",  "Hello" ,new Date());

        List<Chat> chats = new ArrayList<>();
        chats.add(chat1);
        chats.add(chat2);
        when(messagingService.getAllMessages() ).thenReturn(chats);
        ResponseEntity<Response> result =  chatController.getAllMessages();
        ArrayList<Chat>  listChat = (ArrayList<Chat>) result.getBody().getData().get("messages");

        assertEquals( listChat.size() , 2 );
        assertEquals( listChat.get(0).getFrom(), chat1.getFrom() );
        assertEquals( listChat.get(1).getMessage() , chat2.getMessage() );
        assertEquals( listChat.get(1).getDate() , chat2.getDate() );
    }



}
