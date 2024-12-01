//LogParsingService
package com.example.SecureNet.Service;

import com.example.SecureNet.Model.Intrusion;
import com.example.SecureNet.Repository.IntrusionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LogParsingService {

    @Autowired
    private IntrusionRepository intrusionRepository;

    private static final String SNORT_LOG_FILE_PATH = "/var/log/snort/alerts";
    private static final Pattern SNORT_LOG_PATTERN = Pattern.compile(
            "\\[(\\d+)\\] \\[(\\d+:\\d+:\\d+)\\] \"(.*?)\" (.*?) -> (.*?)"
    );

    @Scheduled(fixedRate = 60000)
    public void parseSnortLogFile() {
        System.out.println("Starting to parse Snort log file: " + SNORT_LOG_FILE_PATH);
        System.out.println("Starting Snort log parsing for intrusions.");

        try (BufferedReader reader = new BufferedReader(new FileReader(SNORT_LOG_FILE_PATH))) {
            String line;
            int lineCount = 0;
            int matchCount = 0;

            while ((line = reader.readLine()) != null) {
                lineCount++;
                System.out.println("Reading line " + lineCount + ": " + line);

                Matcher matcher = SNORT_LOG_PATTERN.matcher(line);
                if (matcher.matches()) {
                    matchCount++;
                    String intrusionType = matcher.group(3);
                    String sourceIp = matcher.group(4);
                    String targetUrl = matcher.group(5);
                    String timestamp = matcher.group(2);

                    Intrusion intrusion = new Intrusion();
                    intrusion.setType(intrusionType);
                    intrusion.setSourceIp(sourceIp);
                    intrusion.setTargetUrl(targetUrl);
                    intrusion.setTimestamp(timestamp);

                    intrusionRepository.save(intrusion);
                    System.out.println("Saved intrusion: " + intrusion);
                } else {
                    System.out.println("No match found for line " + lineCount);
                }
                System.out.println("Snort log entry: " + line);

            }

            System.out.println("Finished parsing the Snort log file. Total lines read: " + lineCount + ", Total matches found: " + matchCount);
        } catch (IOException e) {
            System.err.println("Error reading the Snort log file: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
