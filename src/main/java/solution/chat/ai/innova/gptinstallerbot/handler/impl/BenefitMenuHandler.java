package solution.chat.ai.innova.gptinstallerbot.handler.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import solution.chat.ai.innova.gptinstallerbot.handler.MenuHandler;

import static solution.chat.ai.innova.gptinstallerbot.enums.ServiceCommands.BENEFITS;
import static solution.chat.ai.innova.gptinstallerbot.handler.impl.StartHandler.getStartReplyKeyboardMarkup;

@Component
public class BenefitMenuHandler implements MenuHandler {
    @Override
    public SendMessage getMenu(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String text = """
                З нашим ботом ви зможете:
                • Заощаджувати незліченну кількість годин на нудних завданнях
                • Оптимізувати операцій
                • Звільнити свій час та уникати вигорання
                • Скоротити щомісячні витрати
                • Забезпечити високу якість роботи в масштабах
                                
                Випередити своїх конкурентів
                Ви були в захваті від Штучного інтелекту, а потім відчули, що він не виправдав ваших сподівань?
                Ви не самотні. Але правда в тому, що поточна версія ChatGPT - це лише бета-тест справжньої потужності штучного інтелекту, яка ще попереду.
                Тим не менш, 93% люедй НЕ використовують ChatGPT належним чином. Тому що розблокувати всю потужність ШІ можна лише за допомогою точних запитів, які надають правильні дані.
                """;

        SendMessage message = new SendMessage(chatId, text);

        ReplyKeyboardMarkup keyboardMarkup = getStartReplyKeyboardMarkup();
        message.setReplyMarkup(keyboardMarkup);
        return message;
    }

    @Override
    public boolean isApplicable(String message) {
        return BENEFITS.toString().equals(message);
    }

    @Override
    public PartialBotApiMethod getMessage(Update update) {
        return getMenu(update);
    }
}
