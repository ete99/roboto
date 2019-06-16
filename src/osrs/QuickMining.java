package osrs;


import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import osrs.tasks.*;

import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name="QuickMining", description="Tutorial", properties="client=4; author=Chris; topic=999;")

public class QuickMining extends PollingScript<ClientContext> {

    List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start(){
        taskList.add(new Drop(ctx));
        taskList.add(new Mine(ctx));
    }

    @Override
    public void poll() {

        for(Task task : taskList){
            if(ctx.controller.isStopping()){
                break;
            }

            if(task.activate()){
                task.execute();
                break;
            }
        }

    }

}
