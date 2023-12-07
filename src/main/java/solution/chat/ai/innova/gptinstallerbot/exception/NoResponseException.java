package solution.chat.ai.innova.gptinstallerbot.exception;

import lombok.Getter;

@Getter
public class NoResponseException extends RuntimeException {

    private final Long chatId;

    public NoResponseException(Long chatId) {
        super("No response received from Chat GPT");
        this.chatId = chatId;
    }

}
