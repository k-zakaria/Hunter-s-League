package org.youcode.maska_hunters_league.web.VMs.AuthVMs;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseVM {
    private String accessToken;
}
