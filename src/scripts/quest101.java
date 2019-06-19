package scripts;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import scripts.tasks.*;

import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name="quesast", description="primero", properties="client=4; author=Ete; topic=999;")

public class quest101 extends PollingScript<ClientContext>{

    public List<Task> taskList = new ArrayList<Task>();
    public static SetUp setUp;


    @Override
    public void start(){
        setUp =  new SetUp(ctx, 1);
//        try {
//            Thread.
//            mailme.sendPOST();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        taskList.add(new PosUtil(ctx));
            taskList.add(new CheckValid(ctx));
            taskList.add(new WalkToTree(ctx));
//        taskList.add(new OpenDoor(ctx));
            taskList.add(new Chop(ctx));
            taskList.add(new Antiban(ctx));
            taskList.add(new WalkToBank(ctx));
            taskList.add(new Bank(ctx));
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
