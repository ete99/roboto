package idleChopper;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import idleChopper.common_tasks.Antiban;
import idleChopper.misc_tasks.Tester;

import static idleChopper.Constants.*;
import static idleChopper.quest101.setUp;
import static idleChopper.quest101.taskList;

public class  SetUp {
    public Boolean debug = false;
    public  String TREE_NAME;
    public  String TREE_MESS_NAME;
    public  int TREE_ID;
    public  String LOGS_NAME;  // To drop, possibly deprecated
    public  String AXE_NAME;
    public  int AXE_ID;
    public  int TREE_XP;
    public  Area TREE_AREA;
    public  Area BANK_AREA;
    public  State STATE = State.UNKNOWN;
    public ClientContext ctx;
    public  Tile[] RIDE;
    public int set;
    public boolean mail = false;
    public Boolean run = false;
    public boolean stap =false;

    SetUp (ClientContext ctx){
        this.ctx = ctx;

    }

    public void setTheSetUp(){
        GUI.frame();
        Condition.wait(() -> run, 300, 1000);

        if(setUp.debug)
            taskList.add(new Tester(ctx));
        if(set == 1) {
            this.TREE_AREA = YEW_AREA;
            this.BANK_AREA = EDGE_BANK_AREA;
            this.TREE_NAME = "Yew";
            this.AXE_NAME = "Rune Axe";  //can change(update)
            this.TREE_MESS_NAME = "yew";  //can change(update)
            this.AXE_ID = 1359;  //can change(update)
            this.TREE_XP = 175;
            this.RIDE = Constants.EDGE_TO_BANK_RIDE;
            taskList.add(new idleChopper.common_tasks.CheckValid(ctx));
            taskList.add(new idleChopper.yew_tasks.WalkToTree(ctx));
//        taskList.add(new OpenDoor(ctx));
            taskList.add(new idleChopper.yew_tasks.Chop(ctx));
            taskList.add(new Antiban(ctx));
            taskList.add(new idleChopper.yew_tasks.WalkToBank(ctx));
            taskList.add(new idleChopper.common_tasks.Bank(ctx));
            taskList.add(new idleChopper.common_tasks.Idle(ctx));
        } else if (set == 2){
            this.TREE_ID = MAGIC_ID;
            this.TREE_AREA = MAGIC_FULL_AREA;
            this.BANK_AREA = MAGIC_BANK_AREA;
            this.TREE_NAME = "Magic tree";
            this.AXE_NAME = "Dragon Axe";  //can change(update)
            this.TREE_MESS_NAME = "magic";  //can change(update)
            this.AXE_ID = 6739;  //dragon axe
            this.TREE_XP = 250;
            this.RIDE = MAGIC_TO_BANK_RIDE;
            this.STATE = State.UNKNOWN;
            taskList.add(new idleChopper.common_tasks.CheckValid(ctx));
            taskList.add(new idleChopper.magic_tasks.WalkToTree(ctx));
            taskList.add(new idleChopper.magic_tasks.Chop(ctx));
            taskList.add(new Antiban(ctx));
            taskList.add(new idleChopper.magic_tasks.WalkToBank(ctx));
            taskList.add(new idleChopper.common_tasks.Bank(ctx));
            taskList.add(new idleChopper.common_tasks.Idle(ctx));
        } else if (set == 3){
            this.TREE_ID = MAGIC_ID;
            this.TREE_NAME = "Magic tree";
            this.AXE_NAME = "Dragon Axe";  //can change(update)
            this.TREE_MESS_NAME = "magic";  //can change(update)
            this.AXE_ID = 6739;  //dragon axe
            this.TREE_XP = 250;
            this.RIDE = GUILD_MAGIC_TO_BANK_RIDE;
            this.STATE = State.UNKNOWN;
            taskList.add(new idleChopper.common_tasks.CheckValid(ctx));
            taskList.add(new idleChopper.guild_magic_tasks.WalkToTree(ctx));
            taskList.add(new idleChopper.guild_magic_tasks.Chop(ctx));
            taskList.add(new Antiban(ctx));
            taskList.add(new idleChopper.guild_magic_tasks.WalkToBank(ctx));
            taskList.add(new idleChopper.common_tasks.Bank(ctx));
            taskList.add(new idleChopper.common_tasks.Idle(ctx));
        }
        else{
            System.out.println("no tadavia");
        }
    }

    public enum State {
        IDLE,
        REALLY_IDLE,
        WAITING,
        ANTIBANNING,
        WALKING,
        CHOPPING,
        DROPPING,
        BANKING,
        UNKNOWN;
    }
}
