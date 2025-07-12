package structures.array;

import java.util.ArrayList;

/**
 * @author fengsy
 * @date 4/16/20
 * @Description
 */
public class ArrayListDemo {

    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList(10000);
        for (int i = 0; i < 10000; ++i) {
            users.add(new User());
        }
    }
}
class User{

}