import junit.framework.TestCase;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class FileAccessTest extends TestCase {
    String path = "hdfs://7f0aa72cc63b:8020";
    FileSystem hdfs;

    protected void setUp() throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        System.setProperty("HADOOP_USER_NAME", "root");

        hdfs = FileSystem.get(new URI(path), configuration);
    }

    public void testFileAccess() throws IOException, URISyntaxException {
        assertEquals(hdfs, new FileAccess(path).getHdfs());
    }

    public void testCreateDirectory() throws IOException, URISyntaxException {
        new FileAccess(path).create(path + "/test");
        boolean actual = hdfs.isDirectory(new Path(path + "/test"));
        assertEquals(true, actual);
    }

    public void testCreateFile() throws IOException, URISyntaxException {
        new FileAccess(path).create(path + "/test" + "/file.txt");
        boolean actual = hdfs.isFile(new Path(path + "/test" + "/file.txt"));
        assertEquals(true, actual);
    }

    public void testAppend1() throws IOException, URISyntaxException {
        FileAccess fileAccess = new FileAccess(path);
        fileAccess.create(path + "/test/file.txt");
        fileAccess.append(path + "/test/file.txt", "Hello Hadoop!");
        BufferedReader br = new BufferedReader(new InputStreamReader(hdfs.open(new Path(path + "/test/file.txt"))));
        String actual = br.readLine();
        br.close();
        assertEquals("Hello Hadoop!", actual);
    }

    public void testAppend2() throws IOException, URISyntaxException {
        FileAccess fileAccess = new FileAccess(path);
        fileAccess.create(path + "/test/file.txt");
        fileAccess.append(path + "/test/file.txt", "Hello Hadoop!");
        fileAccess.append(path + "/test/file.txt", "Hello Hadoop!");
        fileAccess.append(path + "/test/file.txt", "Hello Hadoop!");
        BufferedReader br = new BufferedReader(new InputStreamReader(hdfs.open(new Path(path + "/test/file.txt"))));
        String actual = br.readLine();
        br.close();
        assertEquals("Hello Hadoop!Hello Hadoop!Hello Hadoop!", actual);
    }

    public void testRead() throws IOException, URISyntaxException {
        FileAccess fileAccess = new FileAccess(path);
        fileAccess.create(path + "/test/file.txt");
        fileAccess.append(path + "/test/file.txt", "Hello Hadoop!");
        String actual = fileAccess.read(path + "/test/file.txt");
        assertEquals("Hello Hadoop!", actual);
    }

    public void testDelete() throws IOException, URISyntaxException {
        hdfs.create(new Path(path + "/test/file.txt"));
        FileAccess fileAccess = new FileAccess(path);
        fileAccess.delete(path + "/test/file.txt");
        boolean actual = hdfs.exists(new Path(path + "/test/file.txt"));
        assertEquals(false, actual);
    }

    public void testIsDirectory() throws IOException, URISyntaxException {
        hdfs.mkdirs(new Path(path + "/test"));
        FileAccess fileAccess = new FileAccess(path);
        assertEquals(hdfs.isDirectory(new Path(path + "/test")), fileAccess.isDirectory(path + "/test"));
    }

    public void testList() throws IOException, URISyntaxException {
        FileAccess fileAccess = new FileAccess(path);
        fileAccess.create(path + "/test/file.txt");
        fileAccess.create(path + "/test/newFolder/anotherFile.txt");
        fileAccess.create(path + "/test/newFolder/lastFolder/text.txt");
        List<String> actual = fileAccess.list(path + "/test");
        List<String> expected = new ArrayList<>();
        expected.add(path + "/test/file.txt");
        expected.add(path + "/test/newFolder/anotherFile.txt");
        expected.add(path + "/test/newFolder/lastFolder/text.txt");
        assertEquals(expected, actual);
    }
}
