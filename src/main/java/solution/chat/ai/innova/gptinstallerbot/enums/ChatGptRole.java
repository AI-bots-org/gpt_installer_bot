package solution.chat.ai.innova.gptinstallerbot.enums;

public enum ChatGptRole {
    USER,
    ASSISTANT,
    SYSTEM;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}