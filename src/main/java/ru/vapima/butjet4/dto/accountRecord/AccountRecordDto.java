package ru.vapima.butjet4.dto.accountRecord;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class AccountRecordDto {
    private Long id;
    private Long amount;
    private String description;
    private LocalDateTime dateTime;
}




