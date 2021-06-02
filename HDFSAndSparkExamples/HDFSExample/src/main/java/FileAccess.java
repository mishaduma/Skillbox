import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class FileAccess {
    private List<String> fileList = new ArrayList<>();
    private FileSystem hdfs;

    public FileSystem getHdfs() {
        return hdfs;
    }

    /**
     * Initializes the class, using rootPath as "/" directory
     *
     * @param rootPath - the path to the root of HDFS,
     *                 for example, hdfs://localhost:32771
     */
    public FileAccess(String rootPath) throws URISyntaxException, IOException {
        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        System.setProperty("HADOOP_USER_NAME", "root");

        this.hdfs = FileSystem.get(new URI(rootPath), configuration);
    }

    /**
     * Creates empty file or directory
     *
     * @param path
     */
    public void create(String path) throws IOException {
        if (path.substring(path.lastIndexOf("/")).split("\\.").length > 1) {
            hdfs.create(new Path(path));
        } else {
            hdfs.mkdirs(new Path(path));
        }
    }

    /**
     * Appends content to the file
     *
     * @param path
     * @param content
     */
    public void append(String path, String content) throws IOException {
        StringBuilder builder = new StringBuilder();
        if (hdfs.exists(new Path(path))) {
            BufferedReader br = new BufferedReader(new InputStreamReader(hdfs.open(new Path(path))));
            String line;
            if ((line = br.readLine()) != null) {
                builder.append(line);
            }
            br.close();
        }
        builder.append(content);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(hdfs.create(new Path(path))));
        bw.write(builder.toString());
        bw.close();
    }

    /**
     * Returns content of the file
     *
     * @param path
     * @return
     */
    public String read(String path) throws IOException {
        String result = "";
        if (hdfs.exists(new Path(path))) {
            BufferedReader br = new BufferedReader(new InputStreamReader(hdfs.open(new Path(path))));
            result = br.readLine();
            br.close();
        }
        return result;
    }

    /**
     * Deletes file or directory
     *
     * @param path
     */
    public void delete(String path) throws IOException {
        hdfs.delete(new Path(path), true);
    }

    /**
     * Checks, is the "path" is directory or file
     *
     * @param path
     * @return
     */
    public boolean isDirectory(String path) throws IOException {
        if (hdfs.isDirectory(new Path(path))) {
            return true;
        }
        return false;
    }

    /**
     * Return the list of files and subdirectories on any directory
     *
     * @param path
     * @return
     */
    public List<String> list(String path) throws IOException {
        RemoteIterator<LocatedFileStatus> fileStatus = hdfs.listFiles(new Path(path), true);
        while (fileStatus.hasNext()) {
            LocatedFileStatus status = fileStatus.next();
            fileList.add(status.getPath().toString());
        }
        return fileList;
    }
}
