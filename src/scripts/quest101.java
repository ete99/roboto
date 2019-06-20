package scripts;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    @Override
    public void start(){
        //@TODO use menus

//        System.out.println(dtf.format(now)); //2016/11/16 12:08:43

        setUp =  new SetUp(ctx, 1, false);  // sets up the needed shared data
        sendMail("Comienza "+ctx.players.local().name().toString() + " " + dtf.format(now));
        if(setUp.debug)
            taskList.add(new Tester(ctx));
        taskList.add(new CheckValid(ctx));
        taskList.add(new WalkToTree(ctx));
//        taskList.add(new OpenDoor(ctx));
        taskList.add(new Chop(ctx));
        taskList.add(new Antiban(ctx));
        taskList.add(new WalkToBank(ctx));
        taskList.add(new Bank(ctx));
        taskList.add(new Idle(ctx));
    }

    public void stop(){
        System.out.println("paro\n");
        sendMail("Paro: "+ ctx.players.local().name().toString() + " " + dtf.format(now));
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
