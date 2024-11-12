package com.example.hunters_league.web.vm;

import lombok.Data;
import java.util.UUID;

@Data
public class HuntVM {
    private UUID id;
    private UUID speciesId;
    private Double weight;
    private UUID participationId;
}
