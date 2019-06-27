package idleChopper;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.powerbot.script.*;
import org.powerbot.script.rt4.ClientContext;

import idleChopper.utility.Task;

import static idleChopper.script.Util.sendMail;

@Script.Manifest(name="quesast", description="Yew chopper for OSRS", properties="client=4; author=Ete; topic=999;")

public class quest101 extends PollingScript<ClientContext> implements PaintListener, MessageListener {

    public static List<Task> taskList = new ArrayList<Task>();
    public static SetUp setUp = null;
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
    @Override
    public void stop(){
        GUI.frame.setVisible(false);
        GUI.frame.dispose();
        System.out.println("paro\n");
        sendMail("Paro: "+ ctx.players.local().name().toString() + " " + dtf.format(LocalDateTime.now()));
    }

    @Override
    public void poll() {
        for(Task task : taskList){

            if(ctx.controller.isStopping()){
                break;
            }

            try {
                if(task.activate()){
                    task.execute();
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                stop();
            }
        }
    }

    @Override
    public void repaint(Graphics graphics) {
        if(setUp!=null && setUp.run)
            GUI.rep(graphics, ctx);
    }

    public void messaged(MessageEvent me) {
        String msg = me.text();
        if(msg.equals("You get some "+ setUp.TREE_MESS_NAME+" logs."))
        {
            GUI.logsChopped++;
            if(setUp != null)
                GUI.expGained+=setUp.TREE_XP;
        }
    }
}
