import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Tasker extends RecursiveTask<String> {

    private Source source;

    public Tasker(Source source){
        this.source = source;
    }

    @Override
    protected String compute() {
        String name = source.getName();

        List<Tasker> taskList = new ArrayList<>();
        for (Source child : source.getChildren()){
            Tasker task = new Tasker(child);
            task.fork();
            taskList.add(task);
        }
        for (Tasker task : taskList){
            name += " " + task.join();
        }
        return name;
    }
}
