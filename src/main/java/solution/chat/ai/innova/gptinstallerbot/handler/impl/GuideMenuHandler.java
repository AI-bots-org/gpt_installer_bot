package solution.chat.ai.innova.gptinstallerbot.handler.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import solution.chat.ai.innova.gptinstallerbot.handler.MenuHandler;

import static solution.chat.ai.innova.gptinstallerbot.enums.ServiceCommands.GUIDE;
import static solution.chat.ai.innova.gptinstallerbot.handler.impl.StartHandler.getStartReplyKeyboardMarkup;

@Component
public class GuideMenuHandler implements MenuHandler {
    private static final String GUIDE_COMMAND = "Як бот може допомогти?";

    @Override
    public SendMessage getMenu(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String text = """
                Як бот може допомогти? - Ось деякі запити, які може робити ChatGPT, про які ви, ймовірно, навіть не здогадувалися:
                  - Генерувати ідеї для оновлення креативів
                  - Досліджуйте швидкозростаючі ринки та аудиторії для таргетування
                  - Писати щотижневий інформаційний бюлетень, орієнтований на особистість
                  - Мозковий штурм ідей для продуктів
                  - Створити стратегію контент-маркетингу
                  - Оптимізуйте рекламу на Facebook та теми електронних листів, щоб збільшити кількість кліків
                  - Створюйте сторінки для збільшення продажів
                  - Створюйте огляди, що звучать по-людськи
                  - Писати порівняння продуктів пліч-о-пліч
                  - Запропонувати питання для інтерв'ю в подкасті
                  - Досліджуйте мета-теги та пишіть описи, зручні для SEO
                  - Створюйте послідовності ManyChat
                  - Створювати послівні скрипти для YouTube та відеороликів
                  - Писати SMS-розсилки, нагадування та подальші дії
                  - Створення висококонверсійних скриптів продажів
                  - Оптимізуйте біографії та пости в соціальних мережах для збільшення залученості
                  - Визначте свій унікальний голос бренду
                  - Перекладати тексти веб-сайтів для глобальної аудиторії
                  - Узагальнення даних для звітності та клієнтських дзвінків
                Витрачайте менше часу на виконання нудних, повторюваних завдань;
                Проводьте більше часу в зоні геніальності (тобто стратегічне мислення, творчість, індивідуальне спілкування);
                Подвоїти або потроїти кількість клієнтів, яких ви обслуговуєте;
                Пропонуйте більше послуг клієнтам;
                Масштабуйся з ощадливою командою.                  
                """;

        SendMessage message = new SendMessage(chatId, text);

        ReplyKeyboardMarkup keyboardMarkup = getStartReplyKeyboardMarkup();
        message.setReplyMarkup(keyboardMarkup);
        return message;
    }

    @Override
    public boolean isApplicable(String message) {
        return GUIDE_COMMAND.equals(message) || GUIDE.toString().equals(message);
    }

    @Override
    public PartialBotApiMethod getMessage(Update update) {
        return getMenu(update);
    }
}
