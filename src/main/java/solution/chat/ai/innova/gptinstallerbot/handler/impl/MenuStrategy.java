package solution.chat.ai.innova.gptinstallerbot.handler.impl;

import java.util.List;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import solution.chat.ai.innova.gptinstallerbot.exception.DataNotFoundException;
import solution.chat.ai.innova.gptinstallerbot.handler.MenuHandler;

@RequiredArgsConstructor
@Component
public class MenuStrategy {
    private final List<MenuHandler> menuHandlers;

    public MenuHandler getHandler(String message) {
        return menuHandlers.stream().filter(h -> h.isApplicable(message))
                .findFirst()
                .orElseThrow(DataNotFoundException::new);
    }
}