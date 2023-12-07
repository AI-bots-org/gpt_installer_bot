package solution.chat.ai.innova.gptinstallerbot.dto.chatgpt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAiMessageDTO {
    private String role;
    private String content;
}
