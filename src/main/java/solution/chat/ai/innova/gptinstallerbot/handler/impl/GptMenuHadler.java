package solution.chat.ai.innova.gptinstallerbot.handler.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import solution.chat.ai.innova.gptinstallerbot.handler.MenuHandler;

@RequiredArgsConstructor
@Component
public class GptMenuHadler implements MenuHandler {

    private static final String QUESTION_COMMAND = "Задати своє питання боту";

    private static final String QUESTION = "Напишіть ваше запитання:";

    @Override
    public SendMessage getMenu(Update update) {
        Long chatId = update.getMessage().getChatId();
        return new SendMessage(chatId.toString(), QUESTION);
    }

    @Override
    public boolean isApplicable(String message) {
        return QUESTION_COMMAND.equals(message);
    }

    @Override
    public PartialBotApiMethod getMessage(Update update) {
        return getMenu(update);
    }
}
