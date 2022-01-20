package by.epam.jwdsc.entity.json;

public record CheckOrderRequestData(String command, String orderNumber, String email, String code) {
}
