package yew;

import org.powerbot.script.*;
import org.powerbot.script.rt4.*;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GeItem;


import java.awt.*;
import java.util.Random;

@Script.Manifest(name = "yew.SYewChopper", properties = "author=Spearless; topic=1333332; client=4;", description = "Cuts Yews and banks in East Varrock,DraynorV,GE,SouthFalador,Edgeville")
public class SYewChopper extends PollingScript<ClientContext> implements PaintListener  {

    AREA a = new AREA();

    int YewInvID=1515;
    int BankDr[]={6947,6943,18492,18491,24101};
    GameObject bankDr= ctx.objects.select().id(BankDr).poll();
    Item YewsLogsItem=ctx.inventory.select().id(YewInvID).poll();
    GameObject stairs= ctx.objects.select().id(16671).poll();

    public void start() {

        GUI.frame(ctx);
    }

    void chopYew(){
        GameObject YewTree= ctx.objects.select().id(1753).nearest().poll();
        if(ctx.players.local().animation()==-1 && ctx.inventory.select().count()<28&& YewTree.inViewport()&& !ctx.players.local().inMotion()){

            log.info("Chop");
            YewTree.interact("Chop");
            YewTree.click();

        }
    }
    void antiban() {
        Random random = new Random();
      int switcher= random.nextInt(100);
        int x = random.nextInt(1000)-300;
        int y = random.nextInt(1300)-400;

        switch (switcher) {
            case 1:
            ctx.input.move(x, y);

                break;
            case 2:
                ctx.input.move(x,y);
                break;
            case 5:
                ctx.input.move(x,y);
                break;
            case 7:
                ctx.camera.angle('s');
                break;
            case 8:
               ctx.camera.angle('w');
                break;
            case 9:
                ctx.camera.angle('e');
                break;
        }
    }



    public void poll() {
        switch (state()) {

            case CHDRAYNOR:
                antiban();
                ctx.input.speed(100);

                chopYew();
                if (ctx.players.local().animation() == 867||ctx.players.local().animation()==-1) {
                    antiban();
                }
                if (ctx.inventory.select().count() == 28 && !a.Draynor_Bank.contains(ctx.players.local())) {
                    TilePath path = ctx.movement.newTilePath(a.tileToBankDraynor);
                    path.randomize(2, 2);
                    path.traverse();
                    log.info("Going to bank");
                }


                if (ctx.inventory.select().count() == 28 && !ctx.players.local().inMotion()) {
                    YewsLogsItem = ctx.inventory.select().id(YewInvID).poll();

                    log.info("Banking");
                    bankDr.interact("Bank");
                    YewsLogsItem.interact("Deposit-All");
                }
                if (ctx.inventory.select().id(YewInvID).count() == 0 && !a.Draynor_Yew.contains(ctx.players.local())) {

                    log.info("Coming back");
                    TilePath path = ctx.movement.newTilePath(a.tileToTreesDraynor);
                    path.randomize(2, 2);
                    path.traverse();
                }
                break;
            case CHGE:
                antiban();
                chopYew();
                if (ctx.inventory.select().count() == 28 && !a.GE_BANKAREA.contains(ctx.players.local())) {

                    TilePath path = ctx.movement.newTilePath(a.tileToBankGE);
                    path.randomize(2, 2);
                    path.traverse();
                    ctx.camera.angle('n');
                }

                if (ctx.players.local().inMotion() == false && a.GE_BANKAREA.contains(ctx.players.local()) && ctx.inventory.select().count() == 28) {
                    YewsLogsItem = ctx.inventory.select().id(YewInvID).poll();
                    GameObject bankGE = ctx.objects.select().id(10060, 10059).poll();
                    bankGE.interact("Bank");

                    if (ctx.bank.open()) {
                        YewsLogsItem.interact("Deposit-All");

                    }
                }
                if (ctx.inventory.select().id(YewInvID).count() == 0 && !a.GEYew_Area.contains(ctx.players.local())) {

                    TilePath path = ctx.movement.newTilePath(a.TileToTreesGE);
                    path.randomize(2, 2);
                    path.traverse();
                }
                break;
            case CHVEAST:

                antiban();
                chopYew();
                if (ctx.inventory.select().count() == 28 && !a.VEAST_BANK.contains(ctx.players.local())) {

                    TilePath path = ctx.movement.newTilePath(a.TileToBankVEAST);
                    path.randomize(2, 2);
                    path.traverse();
                }
                if (a.VEAST_BANK.contains(ctx.players.local()) && !ctx.players.local().inMotion() && ctx.inventory.select().count() == 28) {
                    GameObject bankers = ctx.objects.select().id(7409).poll();
                    bankers.interact("Bank");
                    YewsLogsItem = ctx.inventory.select().id(YewInvID).poll();

                    if (ctx.bank.open()) {

                        YewsLogsItem.interact("Deposit-All");
                    }
                }
                if (ctx.inventory.select().id(YewInvID).count() == 0 && !a.VARROCK_EAST.contains(ctx.players.local())) {

                    TilePath path = ctx.movement.newTilePath(a.TileToYewVEAST);
                    path.randomize(2, 2);
                    path.traverse();
                }
                break;
            case CHOLUMB:
                chopYew();
                if (!a.STAIR_AREA.contains(ctx.players.local())&&ctx.inventory.select().count() == 28 && !a.LUMB_BANKAREA.contains(ctx.players.local())) {
                    TilePath path = ctx.movement.newTilePath(a.tileToBankLUMB);
                    path.randomize(1, 1);
                    path.traverse();
                }
                    if(a.STAIR_AREA.contains(ctx.players.local())){
                        stairs.click();
                        log.info("Climbing up");
                    }
                    if(a.LUMB_BANKAREA.contains(ctx.players.local())) {
                        bankDr.interact("Bank");
                    if(ctx.bank.open()){
                        YewsLogsItem.interact("Deposit-All");
                    }
                    if(ctx.inventory.select().id(YewInvID).count()==0&& !a.SOUTH_LUMB.contains(ctx.players.local())) {

                        TilePath path1 = ctx.movement.newTilePath(a.TileToTreesLUMB);
                        path1.randomize(1, 1);
                        path1.traverse();
                        GameObject stairs3 = ctx.objects.select().id(16673).poll();
                        if (stairs3.inViewport()) {
                            stairs3.interact("Climb-down");

                        }
                        if (stairs.inViewport()) {
                            stairs.interact("Climb-down");
                        }
                    }

                    }

                break;
            case CHFALADORSOUTH:
                chopYew();
                if(ctx.inventory.select().count()==28&& !a.FALADOR_BANK.contains(ctx.players.local()))
                {
                    log.info("Walking to bank");
                    TilePath path= ctx.movement.newTilePath(a.ToBankFalador);
                path.randomize(2,2);
                    path.traverse();
                }
                if(a.FALADOR_BANK.contains(ctx.players.local())&& ctx.inventory.select().count()==28){
                    bankDr.interact("Bank");
                    log.info("banking");
                    bankDr.click();
                    if(ctx.bank.open()|| ctx.bank.opened()){
                        YewsLogsItem.interact("Deposit-All");

                    }
                }
                if(ctx.inventory.select().id(YewInvID).count()==0&& !a.FALADOR_TREEAREA.contains(ctx.players.local())){
                    TilePath path1=ctx.movement.newTilePath(a.tileToTreesFalador);
                    path1.randomize(2,2);
                    path1.traverse();
                }

                break;
            case CHEDGEVILLE:
                antiban();
                chopYew();
                GameObject YewTree= ctx.objects.select().id(1753).nearest().poll();

                if(!YewTree.inViewport()&&ctx.inventory.select().count()<28&&a.EDGEVILLE_AREA.contains(ctx.players.local())){

                    TilePath path= ctx.movement.newTilePath(a.TILEBETWEEN);

                    path.traverse();
                    ctx.camera.angle('w');
                    log.info("Looking for a tree");

                }
                if(ctx.inventory.select().count()==28 && !a.EDGEVILLE_BANK_AREA.contains(ctx.players.local())){
                    TilePath path1= ctx.movement.newTilePath(a.EDGEVILLE_TILE_TO_BANK);
                    path1.randomize(2,2);
                    path1.traverse();
                    log.info("Walking bank");

                    if(!path1.valid()){
                        GameObject door= ctx.objects.select().id(1543).poll();
                        door.interact("Open");
                    }
                }
                if( ctx.inventory.select().count()==28&&a.EDGEVILLE_BANK_AREA.contains(ctx.players.local())) {
                    ctx.camera.angle('n');
                    bankDr.interact("Bank");
                    log.info("Banking");
                    if (ctx.inventory.select().count() == 28) {
                        YewsLogsItem.interact("Deposit-All");

                    }
                }
                    if (ctx.inventory.select().id(YewInvID).count() == 0 && !a.EDGEVILLE_AREA.contains(ctx.players.local())) {
                        TilePath path3 = ctx.movement.newTilePath(a.TileToTreesEDG);
                        path3.randomize(2,2);
                        path3.traverse();
                    log.info("Walking to trees");
                    }

                break;

        }
    }

    private State state() {
        if ( GUI.choser==1){
            return State.CHDRAYNOR;
        }else if(GUI.choser==2){
            return State.CHGE;
        }else if(GUI.choser==3){
            return State.CHVEAST;
        }else if (GUI.choser==4){
            return State.CHOLUMB;
        }else if(GUI.choser==5){
            return State.CHFALADORSOUTH;
        }else if(GUI.choser==6){
            return State.CHEDGEVILLE;
        }else{
            log.info("Nothing");
            return State.NOTHING;
        }
    }

    @Override
    public void repaint(Graphics graphics) {
        GUI.rep(graphics);
    }

    private enum State{
        CHDRAYNOR,CHOLUMB,NOTHING,CHGE,CHVEAST,CHFALADORSOUTH,CHEDGEVILLE;

    }
}
