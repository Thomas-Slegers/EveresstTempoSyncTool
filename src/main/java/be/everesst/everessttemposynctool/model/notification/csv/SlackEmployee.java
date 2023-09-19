package be.everesst.everessttemposynctool.model.notification.csv;

public record SlackEmployee(String tempoUserName,
                            boolean cgk,
                            boolean dev,
                            String type,
                            String team,
                            String camisUserName,
                            String camisId,
                            String slackUserName) {
}
