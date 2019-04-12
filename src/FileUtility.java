import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Arrays;

public class FileUtility {
	private Path path;
	private int index = 0;
	private List<String> lines;

	public FileUtility(String filename) {
		path = Paths.get(filename);
		if(Files.notExists(path)) throw new Error(filename + " does not exist!");

		try {
			lines = Files.readAllLines(path);
		} catch(IOException ex) {
			throw new Error("Could not read " + path + ": " + ex);
		}
	}

	public String read() {
		if(index >= size()) throw new Error("read past the end of the file, reset before this happens with reset()!");
		return lines.get(index++);
	}

	public void reset() {
		index = 0;
		try {
			lines = Files.readAllLines(path);
		} catch(IOException ex) {
			throw new Error("Could not read " + path + ": " + ex);
		}
	}

	public void write(String line) {
		try {
			Files.write(path, Arrays.asList(line), Charset.forName("UTF-8"), StandardOpenOption.APPEND);
			lines = Files.readAllLines(path);
		} catch(IOException ex) {
			throw new Error("Could not access " + path + ": " + ex);
		}
	}

	public void update(int lineNumber, String line) {
		try {
			lines.set(lineNumber, line);
			Files.write(path, lines, Charset.forName("UTF-8"), StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch(IOException ex) {
			throw new Error("Could not update " + path + ": " + ex);
		}
	}

	public int size() {
		return lines.size();
	}
}
