package jp.cron.sample.api.controller;

import jp.cron.sample.api.JDAProvider;
import jp.cron.sample.api.response.RoleMembersResponse;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
public class ApiController {

    @Autowired
    JDAProvider jdaProvider;

    @GetMapping("role/{guildId}/{roleId}/members")
    public ResponseEntity<RoleMembersResponse> getRoleMembers(@PathVariable String roleId, @PathVariable String guildId) {
        if (!jdaProvider.isReady())
            return ResponseEntity
                    .badRequest()
                    .body(
                            RoleMembersResponse
                                    .builder()
                                    .success(false)
                                    .code(400)
                                    .message("Bot is not ready")
                                    .build()
                    );

        Guild guild;
        try {
            guild = jdaProvider.get().getGuildById(guildId);
        } catch (NumberFormatException e) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            RoleMembersResponse
                                    .builder()
                                    .success(false)
                                    .code(400)
                                    .message("Invalid guild id")
                                    .build()
                    );
        }

        if (guild == null)
            return ResponseEntity
                    .badRequest()
                    .body(
                            RoleMembersResponse
                                    .builder()
                                    .success(false)
                                    .code(400)
                                    .message("Guild not found")
                                    .build()
                    );

        Role role;
        try {
            role = guild.getRoleById(roleId);
        } catch (NumberFormatException e) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            RoleMembersResponse
                                    .builder()
                                    .success(false)
                                    .code(400)
                                    .message("Invalid role id")
                                    .build()
                    );
        }

        if (role == null)
            return ResponseEntity
                    .badRequest()
                    .body(
                            RoleMembersResponse
                                    .builder()
                                    .success(false)
                                    .code(400)
                                    .message("Role not found")
                                    .build()
                    );

        return ResponseEntity.ok(
                RoleMembersResponse
                        .builder()
                        .success(true)
                        .code(200)
                        .message("OK")
                        .data(
                                guild.getMembersWithRoles(role).stream()
                                        .map(member -> member.getUser().getId())
                                        .collect(Collectors.toList())
                        )
                        .build());
    }
}
