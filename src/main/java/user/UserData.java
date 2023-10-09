package user;

import lombok.Data;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@Data
@AllArgsConstructor
public class UserData {
    private String email;
    private String password;
    private String name;

    public static UserData generateUserRandom() {
        String email = RandomStringUtils.randomAlphabetic(7) + "@yandex.ru";
        String password = RandomStringUtils.randomAlphabetic(7);
        String name = RandomStringUtils.randomAlphabetic(7);
        return new UserData(email, password, name);
    }
}