import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class TextFile {

    private Path path;
    private final TreeMap<Integer, String> lines = new TreeMap<>();
    private int currentRow = 1;

    public TextFile(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    public TextFile setPath(Path path) {
        this.path = path;
        return this;
    }

    /**
     *
     * @param str
     * @return
     */
    public TextFile put(String str) {
        put(str, currentRow);
        return this;
    }

    /**
     *
     * @param str
     * @param row
     * @return
     */
    public TextFile put(String str, int row) {
        //
        this.currentRow = row;
        //
        lines.put(row, getLine(row) + str);
        return this;
    }

    /**
     *
     * @param line
     * @return
     */
    public TextFile putLine(String line) {
        putLine(line, currentRow);
        return this;
    }

    /**
     *
     * @param line
     * @param row
     * @return
     */
    public TextFile putLine(String line, int row) {
        put(line, row);
        this.currentRow++;
        return this;
    }

    /**
     *
     * @param row
     * @return
     */
    public TextFile delete(int row) {
        this.lines.put(row, null);
        currentRow = row;
        return this;
    }

    /**
     *
     * @param beginRow
     * @param endRow
     */
    public TextFile delete(int beginRow, int endRow) {
        for(int row = beginRow; row <= endRow; row++) {
            delete(row);
        }
        return this;
    }

    /**
     *
     * @param line
     * @param row
     */
    public TextFile overWrite(String line, int row) {
        this.lines.put(row, line);
        currentRow = row;
        return this;
    }

    /**
     *
     * @param row
     * @return
     */
    public String getLine(int row) {
        return lines.get(row) == null ? "" : lines.get(row);
    }

    /**
     *
     * @return
     */
    public TextFile nextLine() {
        this.currentRow++;
        return this;
    }

    /**
     *
     * @throws IOException
     */
    public void makeFile() throws IOException {
        //
        if (Files.exists(this.path)) {
            throw new FileAlreadyExistsException(this.path.toString() + " 已經存在");
        }
        Files.createFile(this.path);
        //
        int lastRow = getLastRow();
        if (lastRow > 0) {
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(this.path.toString()), StandardCharsets.UTF_8));
            //
            for (int i = 1; i < lastRow; i++) {
                String line = getLine(i);
                bw.write(line);
                bw.newLine();
            }
            //
            String line = getLine(lastRow);
            bw.write(line);
            //
            bw.close();
        }
    }

    /**
     *
     * @param path
     * @throws IOException
     */
    public void makeFile(Path path) throws IOException {
        this.path = path;
        makeFile();
    }

    public int getLastRow() {
        return lines.isEmpty() ? 0 : lines.lastKey();
    }

}
