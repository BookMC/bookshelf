package org.bookmc.abstraction.registry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class BindingRegistry {
    private static final Logger LOGGER = LogManager.getLogger(BindingRegistry.class);
    private static final Map<String, Object> bindings = new HashMap<>();
    private static final JsonParser parser = new JsonParser();

    static {
        loadFromJson(System.getProperty("book.binding.binds_file", "/bindings/binds.json"));
    }

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> clazz) {
        if (!clazz.isInterface()) {
            throw new IllegalStateException("You must request an interface to get a binded instance! (" + clazz.getName() + " is not an interface!)");
        }

        String interfaceName = clazz.getName();
        if (bindings.containsKey(interfaceName)) {
            return (T) bindings.get(interfaceName);
        }
        throw new IllegalStateException("Failed to get instance of " + clazz.getName());
    }

    private static void loadFromJson(String path) {
        try (InputStream stream = BindingRegistry.class.getResourceAsStream(path)) {
            if (stream == null) {
                throw new IllegalStateException("Failed to load binding registry, missing file: " + path);
            }

            try (InputStreamReader reader = new InputStreamReader(stream)) {
                JsonObject object = parser.parse(reader).getAsJsonObject();

                for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                    String interfaceName = entry.getKey();
                    String bindingName = entry.getValue().getAsString();

                    Class<?> clazz = Class.forName(bindingName);

                    bindings.put(interfaceName, clazz.getConstructor().newInstance());
                    LOGGER.info("Successfully binded {} to {}", interfaceName, bindingName);
                }
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
