package scripts;

import org.powerbot.script.Area;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import scripts.tasks.*;
import scripts.tasks.Util.PosUtil;

import java.util.ArrayList;
import java.util.List;

import static scripts.Constants.*;
import static scripts.SetUp.*;

@Script.Manifest(name="quesast", description="primero", properties="client=4; author=Ete; topic=999;")

public class quest101 extends PollingScript<ClientContext> {

    public List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start(){
//        taskList.add(new PosUtil(ctx));

            taskList.add(new WalkToTree(ctx, TREE_AREA, EDGE_TO_BANK_RIDE));
//        taskList.add(new OpenDoor(ctx));
            taskList.add(new Chop(ctx, TREE_AREA, TREE_NAME));
            taskList.add(new Antiban(ctx));
            taskList.add(new WalkToBank(ctx, EDGE_BANK_AREA, EDGE_TO_BANK_RIDE));
            taskList.add(new Bank(ctx, AXE_NAME));
            taskList.add(new Idle(ctx));


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
