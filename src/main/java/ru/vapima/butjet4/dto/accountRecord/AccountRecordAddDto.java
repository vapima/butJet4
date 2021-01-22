package ru.vapima.butjet4.dto.accountRecord;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class AccountRecordAddDto {
    @NotNull
    private Long amount;
    @NotBlank
    @Size(min=3,max=30)
    private String description;
    private LocalDateTime dateTime;
}




