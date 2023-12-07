package solution.chat.ai.innova.gptinstallerbot.handler;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MenuHandler {
    SendMessage getMenu(Update update);

    boolean isApplicable(String message);

    PartialBotApiMethod getMessage(Update update);

}