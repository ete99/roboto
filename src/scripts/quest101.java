package scripts;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import scripts.utility.Task;

import static scripts.script.Util.sendMail;

@Script.Manifest(name="quesast", description="Yew chopper for OSRS", properties="client=4; author=Ete; topic=999;")

public class quest101 extends PollingScript<ClientContext> implements PaintListener {

    public static List<Task> taskList = new ArrayList<Task>();
    public static SetUp setUp;
    public ClientContext ct = ctx;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    @Override
    public void start(){
        //@TODO use menus

//        System.out.println(dtf.format(now)); //2016/11/16 12:08:43

        setUp =  new SetUp(ctx);  // sets up the needed shared data
        setUp.setTheSetUp();
        sendMail("Comienza "+ctx.players.local().name().toString() + " " + dtf.format(now));
    }

    public void stop(){
        System.out.println("paro\n");
        sendMail("Paro: "+ ctx.players.local().name().toString() + " " + dtf.format(LocalDateTime.now()));
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

    @Override
    public void repaint(Graphics graphics) {
        GUI.rep(graphics, ctx);
    }
}
