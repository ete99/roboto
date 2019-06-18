package scripts2;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import scripts2.tasks.Util.PosUtil;
import scripts2.tasks.*;

import java.util.ArrayList;
import java.util.List;

import static scripts2.Constants.WILLOW_AREA;

@Script.Manifest(name="South Chop", description="primero", properties="client=4; author=Ete; topic=999;")

public class quest101 extends PollingScript<ClientContext> {

    List<scripts2.Task> taskList = new ArrayList<scripts2.Task>();

    @Override
    public void start(){
//        taskList.add(new PosUtil(ctx));
//        taskList.add(new WalkToTree(ctx));
        taskList.add(new Chop(ctx,WILLOW_AREA,"Willow"));
        taskList.add(new Drop(ctx, "Willow logs"));

//        taskList.add(new WalkToBank(ctx));
//        taskList.add(new Bank(ctx));
        taskList.add(new Antiban(ctx));
        taskList.add(new Idle(ctx));
    }
//        taskList.add(new Drop(ctx, ADAM_AXE));

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
