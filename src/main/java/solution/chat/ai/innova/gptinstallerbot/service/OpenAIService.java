package solution.chat.ai.innova.gptinstallerbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import solution.chat.ai.innova.gptinstallerbot.dto.chatgpt.ChatRequestDTO;
import solution.chat.ai.innova.gptinstallerbot.dto.chatgpt.ChatResponseDTO;
import solution.chat.ai.innova.gptinstallerbot.dto.chatgpt.OpenAiMessageDTO;
import solution.chat.ai.innova.gptinstallerbot.enums.ChatGptRole;
import solution.chat.ai.innova.gptinstallerbot.exception.NoResponseException;


@RequiredArgsConstructor
@Service
public class OpenAIService {

    private final RestTemplate opeanaiRestTemplate;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.model}")
    private String model;

    public String getResponse(String prompt, Long chatId) throws NoResponseException {
        ChatRequestDTO requestDTO = new ChatRequestDTO(model);

        requestDTO.getMessages().add(new OpenAiMessageDTO(ChatGptRole.USER.toString(), prompt));
        ChatResponseDTO responseDTO = opeanaiRestTemplate.postForObject(apiUrl, requestDTO, ChatResponseDTO.class);

        if (responseDTO == null || responseDTO.getChoices() == null || responseDTO.getChoices().isEmpty()) {
            throw new NoResponseException(chatId);
        }
        return responseDTO.getChoices().get(0).getMessage().getContent();
    }
}
