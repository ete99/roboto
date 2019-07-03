package RockySand.goblin_killer;


import RockySand.tasks.*;
import RockySand.utils.sett;

import org.powerbot.script.PaintListener;
import org.powerbot.script.rt4.*;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static RockySand.utils.sett.font;
import static RockySand.utils.sett.substatus;


@Script.Manifest(

        name="Sand Killer",
        description = "jaja",
        properties = "client=4; author=Ete; topic=999;"
)

public class RockySands extends PollingScript<ClientContext> implements PaintListener
{


    private List<Task> taskList = new ArrayList<Task>();
    public static sett s = null;
    @Override
    public void start()
    {
        s = new sett(ctx);
        System.out.println("Starting.");

        taskList.add(new ValidCheck(ctx));
        taskList.add(new WalkToArea(ctx));//3053 3248 - sarim, 3080 3249 edge
        taskList.add(new Rockysand.tasks.Fight(ctx));
        taskList.add(new WalkToBank(ctx));


    }


    @Override
    public void poll()
    {
        for (Task task : taskList)
        {
            if (ctx.controller.isStopping())
            {
                break;
            }

            if (task.activate())
            {
                try {
                    task.execute();
                } catch (Exception e) {
                    this.taskList.clear();
                    stop();
                    System.exit(1);
                }
                break;
            }
        }
    }

    @Override
    public void repaint(Graphics graphics)
    {

        Graphics2D g = (Graphics2D)graphics;

        g.setFont(font);

        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0,20, 240, 50);

        g.setColor(new Color(255,255,255));
        g.drawRect(0, 20,240,50);

        g.drawString(String.format("Status: %s - %s", null, substatus), 10, 65);
    }
}
