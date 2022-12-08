package adventcode.day7;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@NoArgsConstructor
class CommandParser {

    protected boolean isRoot(String line) {
        return line.startsWith("$ cd /");
    }
    
    protected boolean isChangeDirCommand(String line) {
        log.debug("change directory - {}", line);
        return line.startsWith("$ cd ") && !line.startsWith("$ cd ..");
    }
    
    protected boolean isLeaveDirCommand(String line) {
        log.debug("Leave directory - {}", line);
        return line.startsWith("$ cd ..");
    }

    protected boolean isFile(String line) {
        String[] elements = line.split(StringUtils.SPACE);
        return StringUtils.isNumeric(elements[0]);
    }
}
