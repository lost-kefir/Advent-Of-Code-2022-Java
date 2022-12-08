package adventcode.day7;

import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Getter
@ToString
class File {
    private final String name;
    private final Integer size;
    
    File(String line) {
        String[] elements = line.split(StringUtils.SPACE);
        this.size = Integer.parseInt(elements[0]);
        this.name = elements[1];
    }
}
