package service;

import com.chat.socket.dto.Message;
import com.chat.socket.entitys.Chat;
import com.chat.socket.repository.ChatRepository;
import com.chat.socket.service.MessagingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {


    private MessagingService messagingService;
    private ChatRepository chatRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        chatRepository = mock(ChatRepository.class);
        messagingService = new MessagingService(chatRepository);
    }

    @Test
    public void getAllMessages() {
        List<Chat> list = new ArrayList<>();
        list.add( new Chat() );
        list.add( new Chat() );

        when(chatRepository.findAll()).thenReturn(list);
        List<Chat> result = messagingService.getAllMessages();
        assertNotNull(result);
        assertEquals(result.size(), list.size());
    }


    @Test
    public void saveMessage() {
        Message message = new Message();
        message.setFrom("Mihai");
        message.setText("Hi");

        Chat chat =  Chat.builder()
                .message("Hi")
                .Date(new Date() )
                .from("Mihai")
                .build();

        messagingService.saveMessage(message);
        verify(chatRepository, times(1)).save(chat);
    }

}
