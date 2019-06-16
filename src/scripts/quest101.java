package scripts;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import scripts.tasks.*;
import scripts.tasks.Util.PosUtil;

import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name="quesast", description="primero", properties="client=4; author=Ete; topic=999;")

public class quest101 extends PollingScript<ClientContext> {

    List<Task> taskList = new ArrayList<Task>();
    public static int con;

    @Override
    public void start(){
        taskList.add(new WalkToTree(ctx));
        taskList.add(new Chop(ctx));
        taskList.add(new Antiban(ctx));
        taskList.add(new WalkToBank(ctx));
        taskList.add(new Bank(ctx));
//        taskList.add(new PosUtil(ctx));
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
