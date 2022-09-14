import java.util.*;
public class Player{
    //Description : the blood of the player
    private int blood=4;
    //Description : the ID of the player
    private int PLAYERID;
    //Description : the role of the player
    private int ROLE;
    //Description : the hand card set of the player
    private ArrayList<Integer> handCard = new ArrayList<Integer>();
    public Player(int playerId ,int role){
        this.ROLE=role;
        this.PLAYERID=playerId;
        
    }
    
    public int getPlayerId(){
        return this.PLAYERID;
    }
    public int getRole(){
        return this.ROLE;         
    }
    public void setRole(int role){
        this.ROLE=role;
    }
    public int getBlood(){
        return this.blood;
    }
    public void setBlood(int b){
        this.blood=b;
    }
    public ArrayList<Integer> getHandCard(){
        return this.handCard;
    }
    public void setHandCard(ArrayList<Integer> handcard){
        this.handCard=handcard;
    }
    public int HandCardNum(){
        return this.handCard.size();
    }
    //function to get Identify Name
    //team1 ->0,2 team2 ->1,3
    public String getIdentifyName(int role){
        String s="";
        switch(role) {
            case 0:
                s="實習生";
                break;
            case 1:
                s="合夥人";
                break;
            case 2:
                s="社畜";
                break;
            case 3:
                s="慣老闆";
                break;
            default:
                break;
        }
        return(s);
    }
    //Function to get Identify
    public String getIdentify(){
        return getIdentifyName(this.getRole());
    }
    
}
