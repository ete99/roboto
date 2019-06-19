package scripts;

import java.util.ArrayList;
import java.util.List;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import scripts.tasks.*;

import static scripts.script.Util.sendMail;

@Script.Manifest(name="quesast", description="Yew chopper for OSRS", properties="client=4; author=Ete; topic=999;")

public class quest101 extends PollingScript<ClientContext>{

    public List<Task> taskList = new ArrayList<Task>();
    public static SetUp setUp;

    @Override
    public void start(){
        sendMail("Comienza "+ctx.players.local().name());
        setUp =  new SetUp(ctx, 1);  // sets up the needed shared data
//        taskList.add(new Tester(ctx));
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
