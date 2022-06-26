package com.chat.socket.entitys;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity( name = "Chat")
@Table( name = "chat"
       /*
        uniqueConstraints = {
                @UniqueConstraint( columnNames = "from")
        }*/
)
public class Chat {

    @Id
    @SequenceGenerator(
            name = "chat_sequence",
            sequenceName = "chat_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "chat_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;

    private String from;
    private String message;
    private java.util.Date Date;



}