package org.youcode.maska_hunters_league.service.DTOs;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class CompetitionDTO {

    private String code;

    private String location;

    private LocalDateTime date;

    private Long numberOfParticipants;

}
