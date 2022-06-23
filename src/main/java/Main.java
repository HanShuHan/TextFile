import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

//        List<String> list = new LinkedList<>();
//        list.set(2, "2");
//        System.out.println(list);

        Path path = Paths.get("C:\\Users\\luisi\\Desktop\\書涵專用\\FCB\\Eric_Lib\\test.txt");
        TextFile tf = new TextFile(path);
        try {
            tf.put("1-0").put("-1").nextLine().putLine("2-0").putLine("-2", 1)
                    .put("-1").putLine("-2")
                    .nextLine().nextLine()
                    .putLine("4-0").overWrite("4-00", 4).nextLine()
                    .putLine("5-0").delete(5).nextLine()
                    .putLine("6-0")
                    .delete(1, 2)
                    .makeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
