package com.jwen.addressbook.config;

import com.jwen.addressbook.exception.AppConfigurationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author jun.wen
 *
 */
public enum Configurations {
    INSTANCE;

    private final static String KEY_VALUE_SEPARATOR = "=";
    private final static String CONFIGURATION_FILE = "application.conf";
    private final static String OTHER_CONFIGURATION_FILE_KEY = "app_include_file";


    private List<String> otherConfigurationFiles = new ArrayList<>();
    private Map<String, String> configurations = new HashMap<>();

    Configurations() {
        try {
            init();
        } catch (IOException | URISyntaxException e) {
            throw new AppConfigurationException("has error in your application.conf", e);
        }
    }

    private void init() throws IOException, URISyntaxException {

        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(CONFIGURATION_FILE);
        BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream));

        String s = "";
        List<String> lines = new ArrayList<String>();

        while ((s = br.readLine()) != null) {
            lines.add(s);
        }

        // 关闭流
        resourceAsStream.close();
        br.close();

        initConfigurations(lines);
        initOtherConfigurations();

    }

    private void initOtherConfigurations() throws URISyntaxException, IOException {
        for (String fileName : otherConfigurationFiles) {
            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream));


            String s = "";
            List<String> lines = new ArrayList<String>();

            while ((s = br.readLine()) != null) {
                lines.add(s);
            }

            // 关闭流
            resourceAsStream.close();
            br.close();


            initConfigurations(lines);
        }

        otherConfigurationFiles = null;
    }

    private boolean isOtherConfigurationKey(String key) {
        return OTHER_CONFIGURATION_FILE_KEY.equalsIgnoreCase(key);
    }

    private void initConfigurations(List<String> lines) throws URISyntaxException, IOException {
        lines.stream().forEach(line -> {
            int keyValueSeparatorIndex = line.indexOf(KEY_VALUE_SEPARATOR);
            if (isLegalConfigurationLine(line)) {
                String key = line.substring(0, keyValueSeparatorIndex).trim();
                String value = line.substring(keyValueSeparatorIndex + 1, line.length()).trim();
                if (isOtherConfigurationKey(key)) {
                    otherConfigurationFiles.add(value);
                } else {
                    configurations.put(key, value);
                }
            }
        });
    }

    private Pattern pattern = Pattern.compile("^[a-zA-Z](.*?)=(.*?)");
    private boolean isLegalConfigurationLine(String line) {
        return pattern.matcher(line).matches();
    }


    public String get(String key) {
        return configurations.get(key);
    }

    public static void main(String[] args) {

        System.out.println(Configurations.INSTANCE.get("test.on"));
    }
}
