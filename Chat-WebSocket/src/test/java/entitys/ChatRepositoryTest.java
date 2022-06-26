package entitys;

import com.chat.socket.entitys.Chat;
import com.chat.socket.repository.ChatRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChatRepositoryTest {


    private ChatRepository chatRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        chatRepository = mock(ChatRepository.class);
    }

    @Test
    public void save() {
        Chat chat =  Chat.builder()
                .message("Hi")
                .Date(new Date() )
                .from("Tom")
                .build();

        chatRepository.save(chat);
        Assert.assertNotNull(chatRepository.findByFrom("Tom"));
    }


    @Test
    public void findAll() {
        Chat chat1 =  Chat.builder()
                .message("Hi")
                .Date(new Date() )
                .from("Tom1")
                .build();

        Chat chat2 =  Chat.builder()
                .message("Hello")
                .Date(new Date() )
                .from("Tom2")
                .build();
        List<Chat> listExpect = new ArrayList<>();
        listExpect.add(chat1);
        listExpect.add(chat2);
        when(chatRepository.findAll()).thenReturn(listExpect);

        List<Chat> listResult = chatRepository.findAll();
        assertEquals( listResult.get(0).getFrom() , chat1.getFrom() );
        assertEquals( listResult.get(1).getMessage() , chat2.getMessage() );
    }
}