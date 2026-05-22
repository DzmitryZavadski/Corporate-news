package com.message.sending.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsEntity {
    private Long id;
    private String title;
    private String text;
    private LocalDateTime publicationDate;
}
