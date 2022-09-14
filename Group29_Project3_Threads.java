import java.util.ArrayList;
import java.lang.Class;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
//This is the class that implement thread to run the game
public class Group29_Project3_Threads implements Runnable{
    //initialize prepare class
    Prepare prepare=new Prepare();
    //Declare primary class
    private static Primary primary;
    //Declare Thread
    private Thread start;
    //Declare array including the strategy names you load
    private String[] inputStrategys = {"","","","",""};
    //Declare StrategyInterface
    private StrategyInterface strategy;

    public Group29_Project3_Threads(String strategy1, String strategy2, String strategy3, String strategy4){
        super (); 
        // bulid a primary class to get the Player character and role.
        primary = new Primary();
        // add primary Runnable to execute the thread
        start = new Thread(primary);
        // start thread for keep judging the outcome
        start.start();
        this.inputStrategys[0] = strategy1;
        this.inputStrategys[1] = strategy2;
        this.inputStrategys[2] = strategy3;
        this.inputStrategys[3] = strategy4;
        
    }
    //Implement the run function 
    public void run(){
        //initialize the game to Integer type
        int game =0;
        //intitalize the playerid start with 0
        int roundPlayer=0;
        //let the while loop keep running
        int time=0;
        while(game==0){
        /*********************************The TODO (Past)********************************************
         * 
         * TODO(Past): Let the roundPlayer keep looping between 0-3.
         * 
         * roundPlayer equals 0   -> playerid now playing is 0  (yourself)
         * roundPlayer equals 1-3 -> playerid now playing is 1-3(computer)
         * 
         * Requests:
         * 1. You need to print out the playerid and blood which player is playing now with following pattern.
         *    **********
         *    now playing: player 1
         *    blood: 4
         *    ...
         * 
         * Hint:
         * 1. In next TODO, you are supposed to change roundPlayer by roundPlayer++.
         *    However,there are only 4 roundPlayer at most.It is the purpose of this Todo.
         * 2. The Players you can get information are announced in Primary, where We provide method--getPlayer().
         *    Check out those method in Player. (The maximum blood is four points.)
         *    e.g. primary.getPlayer(roundPlayer).getBlood()
         ***********************************The End of the TODO*************************************/
        /********************************************************************************************
         START OF YOUR CODE
         ********************************************************************************************/
         if(roundPlayer!=4){
            System.out.println("**********");
            System.out.println("now playing: player "+primary.getPlayer(roundPlayer).getPlayerId());
            System.out.println("blood: "+primary.getPlayer(roundPlayer).getBlood());
         }else{
            roundPlayer=0;
            System.out.println("**********");
            System.out.println("now playing: player "+primary.getPlayer(roundPlayer).getPlayerId());
            System.out.println("blood: "+primary.getPlayer(roundPlayer).getBlood());
         }  







        /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/
        /*********************************The TODO This Time (Checkpoint3)*************************
         * 
         * TODO(1): Implement every Roundplayer's round.
         * 
         * Requests:
         * 1. While starting every player's round, you must draw 2 cards to their handcard.
         * 2. The actions, fold cards and use cards, have been move to the method auto_run(int roundPlayer, Primary primary) in StrategyInterface, hence you have to use auto_run instead of them in every player's round.          
         * 
         * 
         * Hint:
         * 1. Be sure that Roundplayer is alive before taking action. 
         *    If the Roundplayer is already dead, use roundplayer++ to skip the player.
         * 2. Use drawCard(int number) method defined in Prepare class to draw two cards
         *    and set them to player's handcards by using setHandCard(ArrayList<Integer> handcard) method in Player.
         * 3. We provide a method, loadStrategy(String inputStrategy ,Player user,Player playerUserone,Player playerUsertwo,Player playerUserthree), that you can use to implement the StrategyInterface.
         *    You have to input inputStrategys[0] when roundPlayer = 0 , inputStrategys[1] when roundPlayer = 1 and so on.
         * 
         ***********************************The End of the TODO*************************************/
        /********************************************************************************************
         START OF YOUR CODE
         ********************************************************************************************/         
         ArrayList<Integer> newcard = new ArrayList<>();
         if(primary.getPlayer(roundPlayer).getBlood()<=0){

         }else{
            if(time==0){
            newcard = prepare.drawCard(5);
            primary.getPlayer(roundPlayer).setHandCard(newcard);
            }else{
                newcard.addAll(primary.getPlayer(roundPlayer).getHandCard());
                newcard.addAll(prepare.drawCard(2));
                primary.getPlayer(roundPlayer).setHandCard(newcard);
            }
            if(roundPlayer==0){
            	strategy=loadStrategy(inputStrategys[0],primary.getPlayer(0),primary.getPlayer(1),primary.getPlayer(2),primary.getPlayer(3));          
            	strategy.auto_run(roundPlayer,primary);
            }else if(roundPlayer==1){
                strategy=loadStrategy(inputStrategys[1],primary.getPlayer(1),primary.getPlayer(2),primary.getPlayer(3),primary.getPlayer(0));
                strategy.auto_run(roundPlayer,primary);
            }else if(roundPlayer==2){
                strategy=loadStrategy(inputStrategys[2],primary.getPlayer(2),primary.getPlayer(3),primary.getPlayer(0),primary.getPlayer(1));
                strategy.auto_run(roundPlayer,primary);
            }else{
                strategy=loadStrategy(inputStrategys[3],primary.getPlayer(3),primary.getPlayer(0),primary.getPlayer(1),primary.getPlayer(2));
                strategy.auto_run(roundPlayer,primary);
            }
         }
         roundPlayer++;
         time++;









         /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/
            doNothing(1000);
        }
        
    }
    //this function is for pausing the thread in milliseconds
    private static void doNothing(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Unexpected interruption");
            System.exit(0);
        }
    } 
    //function is the implement of Strategy Interface
    public static StrategyInterface loadStrategy(String whichClass ,Player user,Player playerUserone,Player playerUsertwo,Player playerUserthree) {
		Class clazz = null;
        try {
			clazz = Class.forName(whichClass);
            
            // set parameter types
            Class[] params = new Class[4];
            params[1] = Player.class;
            params[2] = Player.class;
            params[3] = Player.class;
            params[0] = Player.class;
            Constructor constructor =  clazz.getConstructor(params);
            // set parameter
            Object[] paramObjs = new Object[4];
            paramObjs[0] = user;
            paramObjs[1] = playerUserone;
            paramObjs[2] = playerUsertwo;
            paramObjs[3] = playerUserthree;
            
            // instance
            return (StrategyInterface) constructor.newInstance(paramObjs);
            
		} catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
		return null;
	}

    
    
}
