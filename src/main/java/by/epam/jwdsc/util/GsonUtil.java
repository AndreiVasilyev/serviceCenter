package by.epam.jwdsc.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The type Gson util.
 */
public final class GsonUtil {

    private static GsonUtil instance;

    private static final String DATE_TIME_FORMAT_PATTERN = "dd MM uuuu HH:mm:ss";

    private final Gson gson;

    private GsonUtil(Gson gson) {
        this.gson = gson;
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static GsonUtil getInstance() {
        if (instance == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
            gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
            Gson gson = gsonBuilder.setPrettyPrinting().create();
            instance = new GsonUtil(gson);
        }
        return instance;
    }

    /**
     * Gets gson.
     *
     * @return the gson
     */
    public Gson getGson() {
        return gson;
    }

    /**
     * The type Local date time serializer.
     */
    static class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_PATTERN);

        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(localDateTime));
        }
    }

    /**
     * The type Local date time deserializer.
     */
    static class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDateTime.parse(json.getAsString(),
                    DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_PATTERN).withLocale(Locale.ENGLISH));
        }
    }
}

