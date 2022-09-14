import java.util.ArrayList;
//This is the class that implement player's actions.(Your play logic)
public class Group29_Project3_Strategy implements StrategyInterface{
    //Declare Player class
    private Player user;
    //Declare handcard ArrayList
    private ArrayList<Integer> handcard;
    //Initialize the Card class
    private Card card = new Card(0);
    //Initialize the GameCardRunner class
    private GameCardRunner gamecardrunner=new GameCardRunner();
    //Initialize the Prepare class
    private Prepare prepare= new Prepare();
    public Group29_Project3_Strategy(Player user,Player playerUserone,Player playerUsertwo,Player playerUserthree){
        this.user=user;
        this.handcard=user.getHandCard();
    }
    public void fold(){

        String cardname;
        int cardId;

        System.out.println("Handcard before I fold: " +this.handcard);
        System.out.println("My blood: "+this.user.getBlood());
        while(handcard.size()>user.getBlood()){
            if(user.getBlood()>3){
                for(int i=handcard.size()-1;i>=0;i--){
                    cardId=handcard.get(i);
                    cardname = card.getCardName(cardId);
                    if(cardname.equals("Different Pay For Equal Work")||cardname.equals("Bar")){
                        int rid=0;
                        rid=handcard.remove(i);
                        prepare.changeHashmap(rid,2);
                        break;
                    }
                }
            }else{
                for(int i=handcard.size()-1;i>=0;i--){
                    cardId=handcard.get(i);
                    cardname = card.getCardName(cardId);
                    if(cardname.equals("Financial Crisis")||cardname.equals("(Oil Shocks")||
                        cardname.equals("Different Pay For Equal Work")){
                        int rid=0;
                        rid=handcard.remove(i);
                        prepare.changeHashmap(rid,2);
                        break;
                    }
                }
            }

        while(handcard.size()>user.getBlood()){
            int rid=0;
            rid=handcard.remove(0);
            prepare.changeHashmap(rid,2);
            }
        }
        System.out.println("Handcard after I fold: " +this.handcard);

    }

    public void useCard(Player chosen,Primary primary){
        ArrayList<Player> enemy = new ArrayList<>();
        ArrayList<Player> mate = new ArrayList<>();
        for(int i = 0;i<4;i++){
            if(primary.getPlayer(i).getRole()%2 != user.getRole()%2){
                enemy.add(primary.getPlayer(i));  
            }else{
                mate.add(primary.getPlayer(i));
            }
        }

        String cardname;
        int cardId;
        System.out.println("Cards in my hand:");
        for(int cardid : handcard){
           System.out.print(card.getCardName(cardid)+"; ");
        }
        System.out.print("\n");
        int count_fire=0;
        int count_Miss=0;
        int count_fin=0;

        for(int i=handcard.size()-1;i>=0;i--){
            cardId=handcard.get(i);
            cardname = card.getCardName(cardId);
            if(cardname.equals("Fire!")){
                count_fire++;
            }
        }
        for(int i=handcard.size()-1;i>=0;i--){
            cardId=handcard.get(i);
            cardname = card.getCardName(cardId);
            if(cardname.equals("Miss!")){
                count_Miss++;
            }
        }
        for(int i=handcard.size()-1;i>=0;i--){
            cardId=handcard.get(i);
            cardname = card.getCardName(cardId);
            if(cardname.equals("Financial Crisis")){
                count_fin++;
            }
        }        
//----------------------------
        boolean run=true;
        while(run){
            int next=0;
            if(user.getBlood()>3){
                if((count_Miss>=2 || count_fire>2)){
                    for(int i=user.HandCardNum()-1;i>=0;i--){
                        cardId=handcard.get(i);
                        cardname = card.getCardName(cardId);
                        if(cardname.equals("Please Support Counter")){
                            gamecardrunner.applyCard(primary,user,chosen,cardId,cardname,0);
                            next=1;
                            break;
                        }
                    }
                    if(next==1){
                        continue;
                    }
                    for(int i=user.HandCardNum()-1;i>=0;i--){
                        cardId=handcard.get(i);
                        cardname = card.getCardName(cardId);
                        if(cardname.equals("Tonight I Want Some")){
                            gamecardrunner.applyCard(primary,user,chosen,cardId,cardname,0);
                            next=1;
                            break;
                        }
                    }
                    if(next==1){
                        continue;
                    }
                    for(int i=user.HandCardNum()-1;i>=0;i--){
                        cardId=handcard.get(i);
                        cardname = card.getCardName(cardId);
                        if (cardname.equals("Oil Shocks")){
                            gamecardrunner.applyCard(primary,user,chosen,cardId,cardname,0);
                            next=1;
                            break;
                        }
                    }
                    if(next==1){
                        continue;
                    }
                    for(int i=user.HandCardNum()-1;i>=0;i--){
                        cardId=handcard.get(i);
                        cardname = card.getCardName(cardId);
                        if(cardname.equals("beibigennosuke")){
                            gamecardrunner.applyCard(primary,user,chosen,cardId,cardname,1);
                            next=1;
                            break;
                        }
                    }
                    if(next==1){
                        continue;
                    }                    
                    for(int i=user.HandCardNum()-1;i>=0;i--){
                        cardId=handcard.get(i);
                        cardname = card.getCardName(cardId);
                        if(cardname.equals("awpjopabemarrrr")){
                            gamecardrunner.applyCard(primary,user,chosen,cardId,cardname,1);
                            next=1;
                            break;
                        }
                    }
                    if(next==1){
                        continue;
                    }
                    for(int i=user.HandCardNum()-1;i>=0;i--){
                        cardId=handcard.get(i);
                        cardname = card.getCardName(cardId);
                        if(cardname.equals("Financial Crisis")){
                            gamecardrunner.applyCard(primary,user,chosen,cardId,cardname,0);
                            next=1;
                            break;
                        }
                    }
                    if(next==1){
                        continue;
                    }
                    for(int i=user.HandCardNum()-1;i>=0;i--){
                        cardId=handcard.get(i);
                        cardname = card.getCardName(cardId);
                        if(cardname.equals("Fire!")){
                            if(enemy.get(0).getBlood()!=0 && enemy.get(1).getBlood()!=0){
                                gamecardrunner.applyCard(primary,user,enemy.get(0),cardId,cardname,1);
                                next=1;
                                break;
                            }else if(enemy.get(0).getBlood()!=0 && enemy.get(1).getBlood()==0){
                                gamecardrunner.applyCard(primary,user,enemy.get(0),cardId,cardname,1);
                                next=1;
                                break;
                            }else if(enemy.get(1).getBlood()!=0 && enemy.get(0).getBlood()==0){
                                gamecardrunner.applyCard(primary,user,enemy.get(1),cardId,cardname,1);
                                next=1;
                                break;
                            }else if(enemy.get(1).getBlood()==0 && enemy.get(0).getBlood()==0){
                                break;
                            }
                        }
                    }
                    if(next==1){
                        continue;
                    }                   
                }else{    
                    for(int i=user.HandCardNum()-1;i>=0;i--){
                        cardId=handcard.get(i);
                        cardname = card.getCardName(cardId);
                        if(cardname.equals("Please Support Counter")){
                            gamecardrunner.applyCard(primary,user,chosen,cardId,cardname,0);
                            next=1;
                            break;
                        }
                    }
                    if(next==1){
                        continue;
                    }
                    for(int i=user.HandCardNum()-1;i>=0;i--){
                        cardId=handcard.get(i);
                        cardname = card.getCardName(cardId);
                        if(cardname.equals("Tonight I Want Some")){
                            gamecardrunner.applyCard(primary,user,chosen,cardId,cardname,0);
                            next=1;
                            break;
                        }
                    }
                    if(next==1){
                        continue;
                    } 
                    for(int i=user.HandCardNum()-1;i>=0;i--){
                        cardId=handcard.get(i);
                        cardname = card.getCardName(cardId);
                        if (cardname.equals("Oil Shocks")){
                            gamecardrunner.applyCard(primary,user,chosen,cardId,cardname,0);
                            next=1;
                            break;
                        }
                    }
                    if(next==1){
                        continue;
                    }
                    for(int i=user.HandCardNum()-1;i>=0;i--){
                        cardId=handcard.get(i);
                        cardname = card.getCardName(cardId);
                        if(cardname.equals("beibigennosuke")){
                            gamecardrunner.applyCard(primary,user,chosen,cardId,cardname,1);
                            next=1;
                            break;
                        }
                    }
                    if(next==1){
                        continue;
                    }
                    for(int i=user.HandCardNum()-1;i>=0;i--){
                        cardId=handcard.get(i);
                        cardname = card.getCardName(cardId);
                        if(cardname.equals("awpjopabemarrrr")){
                            gamecardrunner.applyCard(primary,user,chosen,cardId,cardname,1);
                            next=1;
                            break;
                        }
                    }
                    if(next==1){
                        continue;
                    } 
                    for(int i=user.HandCardNum()-1;i>=0;i--){
                        cardId=handcard.get(i);
                        cardname = card.getCardName(cardId);
                        if(cardname.equals("Fire!")){
                            if(enemy.get(0).getBlood()!=0 && enemy.get(1).getBlood()!=0){
                                gamecardrunner.applyCard(primary,user,enemy.get(0),cardId,cardname,1);
                                next=1;
                                break;
                            }else if(enemy.get(0).getBlood()!=0 && enemy.get(1).getBlood()==0){
                                gamecardrunner.applyCard(primary,user,enemy.get(0),cardId,cardname,1);
                                next=1;
                                break;
                            }else if(enemy.get(1).getBlood()!=0 && enemy.get(0).getBlood()==0){
                                gamecardrunner.applyCard(primary,user,enemy.get(1),cardId,cardname,1);
                                next=1;
                                break;
                            }else if(enemy.get(1).getBlood()==0 && enemy.get(0).getBlood()==0){
                                break;
                            }
                        }
                    }
                    if(next==1){
                        continue;
                    }
                }
            }else{
                for(int i=user.HandCardNum()-1;i>=0;i--){
                    cardId=handcard.get(i);
                    cardname = card.getCardName(cardId);
                    if(cardname.equals("Please Support Counter")){
                        gamecardrunner.applyCard(primary,user,chosen,cardId,cardname,0);
                        next=1;
                        break;
                    }
                }
                if(next==1){
                    continue;
                }
                for(int i=user.HandCardNum()-1;i>=0;i--){
                    cardId=handcard.get(i);
                    cardname = card.getCardName(cardId);
                    if(cardname.equals("Tonight I Want Some")){
                        gamecardrunner.applyCard(primary,user,chosen,cardId,cardname,0);
                        next=1;
                        break;
                    }
                }
                if(next==1){
                    continue;
                }
                for(int i=user.HandCardNum()-1;i>=0;i--){
                    cardId=handcard.get(i);
                    cardname = card.getCardName(cardId);
                    if(cardname.equals("Gig Economy")){
                        gamecardrunner.applyCard(primary,user,chosen,cardId,cardname,0);
                        next=1;
                        break;
                    }
                }
                if(next==1){
                    continue;
                }                
                for(int i=user.HandCardNum()-1;i>=0;i--){
                    cardId=handcard.get(i);
                    cardname = card.getCardName(cardId);
                    if(cardname.equals("Bar")){
                        if(mate.get(0).getBlood()<=3 && user.getBlood()<=3){
                            gamecardrunner.applyCard(primary,user,chosen,cardId,cardname,0);
                            next=1;
                            break;
                        }
                    }
                }
                if(next==1){
                    continue;
                }
                for(int i=user.HandCardNum()-1;i>=0;i--){
                    cardId=handcard.get(i);
                    cardname = card.getCardName(cardId);
                    if(cardname.equals("beibigennosuke")){
                        gamecardrunner.applyCard(primary,user,chosen,cardId,cardname,1);
                        next=1;
                        break;
                    }
                }
                if(next==1){
                    continue;
                }
                for(int i=user.HandCardNum()-1;i>=0;i--){
                    cardId=handcard.get(i);
                    cardname = card.getCardName(cardId);
                    if(cardname.equals("awpjopabemarrrr")){
                        gamecardrunner.applyCard(primary,user,chosen,cardId,cardname,1);
                        next=1;
                        break;
                    }
                }
                if(next==1){
                    continue;
                }
                for(int i=user.HandCardNum()-1;i>=0;i--){
                    cardId=handcard.get(i);
                    cardname = card.getCardName(cardId);
                    if(cardname.equals("Fire!")){
                        if(enemy.get(0).getBlood()!=0 && enemy.get(1).getBlood()!=0){
                            gamecardrunner.applyCard(primary,user,enemy.get(0),cardId,cardname,1);
                            next=1;
                            break;
                        }else if(enemy.get(0).getBlood()!=0 && enemy.get(1).getBlood()==0){
                            gamecardrunner.applyCard(primary,user,enemy.get(0),cardId,cardname,1);
                            next=1;
                            break;
                        }else if(enemy.get(1).getBlood()!=0 && enemy.get(0).getBlood()==0){
                            gamecardrunner.applyCard(primary,user,enemy.get(1),cardId,cardname,1);
                            next=1;
                            break;
                        }else if(enemy.get(1).getBlood()==0 && enemy.get(0).getBlood()==0){
                            break;
                        }
                    }
                }
                if(next==1){
                    continue;
                }
                for(int i=user.HandCardNum()-1;i>=0;i--){
                    cardId=handcard.get(i);
                    cardname = card.getCardName(cardId);
                    if(cardname.equals("Different Pay For Equal Work")){
                        if(enemy.get(0).getBlood()==0 || enemy.get(1).getBlood()==0)
                            gamecardrunner.applyCard(primary,user,chosen,cardId,cardname,0);
                        next=1;
                        break;
                    }
                }
                if(next==1){
                    continue;
                }

            }
            run=false;
        }
    }

    public void auto_run(int roundPlayer, Primary primary){

        ArrayList<Player> ops = new ArrayList<>();

        if(roundPlayer ==0){
            for(int i = 0;i<4;i++){
                if(primary.getPlayer(i).getRole()%2 != primary.getPlayer(roundPlayer).getRole()%2){
                    ops.add(primary.getPlayer(i));  
                }
            }
        }else if(roundPlayer ==1){
            for(int i = 0;i<4;i++){
                if(primary.getPlayer(i).getRole()%2 != primary.getPlayer(roundPlayer).getRole()%2){
                    ops.add(primary.getPlayer(i));  
                }
            }
        }else if(roundPlayer ==2){
            for(int i = 0;i<4;i++){
                if(primary.getPlayer(i).getRole()%2 != primary.getPlayer(roundPlayer).getRole()%2){
                    ops.add(primary.getPlayer(i));  
                }
            }
        }else if(roundPlayer ==3){
            for(int i = 0;i<4;i++){
                if(primary.getPlayer(i).getRole()%2 != primary.getPlayer(roundPlayer).getRole()%2){
                    ops.add(primary.getPlayer(i));  
                }
            }
        }

        if((ops.get(0).getBlood()!=0 && ops.get(1).getBlood()!=0) && (ops.get(0).getBlood()==ops.get(1).getBlood())){
            useCard(ops.get(0),primary);
            fold();  
        }
        else if((ops.get(0).getBlood()!=0 && ops.get(1).getBlood()!=0) && (ops.get(0).getBlood()<ops.get(1).getBlood())){
            useCard(ops.get(0),primary);
            fold();  
        }
        else if((ops.get(0).getBlood()!=0 && ops.get(1).getBlood()!=0) && (ops.get(0).getBlood()>ops.get(1).getBlood())){
            useCard(ops.get(1),primary);
            fold();  
        }
        else if((ops.get(0).getBlood()==0)){
            useCard(ops.get(1),primary);
            fold();  
        }
        else if((ops.get(1).getBlood()==0)){
            useCard(ops.get(0),primary);
            fold();  
        }         
    }
}
