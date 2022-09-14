import java.util.ArrayList;
//This is the class that implement player's actions.(Your play logic)
public class Group27_Project3_Strategy implements StrategyInterface{
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
    public Group27_Project3_Strategy(Player user,Player playerUserone,Player playerUsertwo,Player playerUserthree){
        this.user=user;
        this.handcard=user.getHandCard();
    }
    public void fold(){
        /*********************************The  TODO last Time (Past)*********************************
         * 
         * TODO(Past): You need to implement your fold card logic here.
         * 
         * Rules: 
         * 1. Whatever card you fold, the final numbers of cards must smaller or equals your blood
         * 2. Every card you fold must be set into fold card deck.
         * 3. You need to show the before,after handcard list and your blood to us with following pattern.
         *    ...
         *    Handcard before I fold: [41,46,78,51,18,44]
         *    My blood: 4
         *    Handcard after I fold: [78,51,46,41]
         * 
         * Hint:
         * 1. You can fold the cards with your own strategy as only as following the rules. 
         * 2. Use changeHashmap(int CardId, int state) method defined in Prepare to change the card state into 2, which
         *    means the card is in the folding deck.
         ***********************************The End of the TODO*************************************/
        /********************************************************************************************
         START OF YOUR CODE
         ********************************************************************************************/

        System.out.println("Handcard before I fold : "+ user.getHandCard());

        while(user.getBlood() < user.HandCardNum() ){
            boolean newRun=false;//this data is use for continue the while loop ,when newrun=1,then restart   
            //logic-start
            
            for(int num=0;num<user.HandCardNum();num++){
                
                if(card.getCardName(user.getHandCard().get(num)).equals("Different Pay For Equal Work")){//if this card is no use 
                    prepare.changeHashmap(this.handcard.get(num),2);//start fold              
                    this.handcard.remove(num);
                    this.user.setHandCard(handcard);//end fold
                    newRun=true;
                    break;//exit this for-loop
                }
            }
            if(newRun){
                continue;//restart whole while 
            }
            int gigcount=0;//this data is to count gig number
            for(int num=0;num<user.HandCardNum();num++){
                if(card.getCardName(user.getHandCard().get(num)).equals("Gig Economy")){
                    gigcount++;
                }
                        
                if(card.getCardName(user.getHandCard().get(num)).equals("Gig Economy")&& gigcount>=3){ //if this card id is gig and gig number is 1 or 2 or 0,then next for loop
                    prepare.changeHashmap(this.handcard.get(num),2);  // if this card id isn't gg or gig number >3 ,then fold this card            
                    this.handcard.remove(num);
                    this.user.setHandCard(handcard);
                    newRun=true;
                    break;
                }   
                else{
                    continue;
                }

            }//this will keep gig eco first
            if(newRun){
                continue;//restart whole while 
            }
            int misscount=0;//this data is to count miss number
            for(int num=0;num<user.HandCardNum();num++){
                if(card.getCardName(user.getHandCard().get(num)).equals("Miss")){
                    misscount++;
                }
                        
                if(card.getCardName(user.getHandCard().get(num)).equals("Gig Economy") ||(card.getCardName(user.getHandCard().get(num)).equals("Miss")) && misscount>=3){ //if this card id is miss,gig and Miss number is 1 or 2 or 0,then next for loop.
                    prepare.changeHashmap(this.handcard.get(num),2);  // if this card id isn't miss , gig or miss number >3 ,then fold this card            
                    this.handcard.remove(num);
                    this.user.setHandCard(handcard);
                    newRun=true;
                    break;
                }   
                else{
                    continue;//which means at least keep 2 miss in hand 
                }
                    
            }//this will keep gig eco first
            if(newRun){
                continue;//restart whole while 
            }        
            //logic-end

            prepare.changeHashmap(this.handcard.get(0),2);              
            this.handcard.remove(0);
            this.user.setHandCard(handcard);
            }

        
        System.out.println("My blood : "+user.getBlood());
        System.out.println("Handcard after I fold : "+ user.getHandCard());










        /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/

    }
    public void useCard(Player chosen,Primary primary){
        /*********************************The  TODO last Time (Past)**********************************
         * 
         * TODO(Past): You need to implement your use card logic here.
         * 
         * Rules: 
         * 1. The card you used must be your handcard.
         * 2. Every card you used must be set into fold card deck.
         * 3. If the player that you "Fire" has "Miss" in their handcard, "Fire" won't have any effect and set into fold card deck.
         * 4. Every card effect has been detailed described in the Project_Introduction document.
         * 5. You need to print out all your handcards' names at the beginning with following pattern.
         *    ...
         *    Cards in my hand: 
         *    Miss; Fire!; Fire!; Fire!; Miss;
         * 
         * Hint:
         * 1. You can use getCardName (Integer cardId) method defined in Card. 
         * 2. You can use card with applyCard(Primary primary, Player user,Player chooseplayer,Integer cardId,String cardName,int useState) method defined in GameCardRunner.
         *    About useState:
         *        if the car ask you to choose a player -> useState = 1
         *        or -> useState = 0
         * 3. You can use the cards in your handCard with your own strategy as only as following the rules.
         *    ex. 1) If you have "Fire", use it.
         *        2) Use all your handCard as you can.
         ***********************************The End of the TODO*************************************/
        /********************************************************************************************
         START OF YOUR CODE
         ********************************************************************************************/
        int death=0;
        int plusRoleNum=0;
        plusRoleNum=chosen.getRole()+user.getRole();
        if(plusRoleNum%2!=0){
        // this person is enemy
            System.out.println("Cards in my hand:");
            for(int num=0;num<user.HandCardNum();num++){
                System.out.print(card.getCardName(user.getHandCard().get(num))+"; ");
            }
            System.out.println();
            boolean stop=false;
            while(!stop){
                if(chosen.getBlood()==0){
                	death=1;
                    break;
                }
                /*************************************************************/
                boolean newRun=false;//this is use for restart  while loop
                /*************************************************************/
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("Please Support Counter")){
                        gamecardrunner.applyCard(primary,user,chosen,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),0);
                        newRun=true;
                        break;
                    }                                  
                }
                if(newRun){
                    continue;
                } 
                /*************************************************************/   
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("Tonight I Want Some")){
                        gamecardrunner.applyCard(primary,user,chosen,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),0);
                        newRun=true;
                        break;
                    }                                  
                }
                if(newRun){
                    continue;
                }
                /*************************************************************/ 
                 for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("beibigennosuke")){
                        if(chosen.HandCardNum()>0){
                            gamecardrunner.applyCard(primary,user,chosen,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),1);
                            newRun=true;    
                            break;                        
                        }
                    }                                  
                }
                if(newRun){
                    continue;
                }   
                /*************************************************************/ 
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("awpjopabemarrrr")){
                        if(chosen.HandCardNum()>0){
                            gamecardrunner.applyCard(primary,user,chosen,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),1);
                            newRun=true;
                            break;                            
                        }
                    }                                  
                }
                if(newRun){
                    continue;
                }
                /*************************************************************/ 
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("Financial Crisis")){
                        gamecardrunner.applyCard(primary,user,chosen,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),0);
                        newRun=true;
                        break;
                    }                                  
                }
                if(newRun){
                    continue;
                } 
                /*************************************************************/
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("Oil Shocks")){
                        gamecardrunner.applyCard(primary,user,chosen,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),0);
                        newRun=true;
                        break;
                    }                                  
                }
                if(newRun){
                    continue;
                }       
                /*************************************************************/
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("Fire!")){
                        gamecardrunner.applyCard(primary,user,chosen,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),1);
                        newRun=true;
         				break;
                    }                                  
                }    
                if(newRun){
                    continue;
                }   
                /*************************************************************/
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("Gig Economy")){
                        if(user.getBlood()<4){
                            gamecardrunner.applyCard(primary,user,chosen,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),0);
                            newRun=true;
                        }
                    }                                  
                }
                if(newRun){
                    continue;
                }        
                stop=true;              
            }
        		/****************************enemy2*********************************/

    if(death==1){ 
        int plusRoleNum1=0;
        int deadid=chosen.getPlayerId();
        int arole=0;
        int anotherid=0;
        Player another;
       if(chosen.getRole()+2>3){
        	arole=chosen.getRole()-2;
        	for(int i=0;i<=3;i++){
        		if(arole==primary.getPlayer(i).getRole()){
        			anotherid=i;
        			break;
        		}
        	}
        	another=primary.getPlayer(anotherid);
        }
        else{
        	for(int i=0;i<=3;i++){
        		if(arole==primary.getPlayer(i).getRole()){
        			anotherid=i;
        			break;
        		}
        	}
        	another=primary.getPlayer(anotherid);
        }
        plusRoleNum1=another.getRole()+user.getRole();
        if(plusRoleNum1%2!=0){
        // this person is another enemy
            boolean stop1=false;
            while(!stop1){
                if(another.getBlood()==0){
                    break;
                }
                /*************************************************************/
                boolean newRun=false;//this is use for restart  while loop
                /*************************************************************/
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("Please Support Counter")){
                        gamecardrunner.applyCard(primary,user,another,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),0);
                        newRun=true;
                    }                                  
                }
                if(newRun){
                    continue;
                } 
                /*************************************************************/   
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("Tonight I Want Some")){
                        gamecardrunner.applyCard(primary,user,another,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),0);
                        newRun=true;
                    }                                  
                }
                if(newRun){
                    continue;
                }
                /*************************************************************/ 
                 for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("beibigennosuke")){
                        if(another.HandCardNum()>0){
                            gamecardrunner.applyCard(primary,user,another,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),1);
                            newRun=true;                            
                        }
                    }                                  
                }
                if(newRun){
                    continue;
                }   
                /*************************************************************/ 
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("awpjopabemarrrr")){
                        if(another.HandCardNum()>0){
                            gamecardrunner.applyCard(primary,user,another,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),1);
                            newRun=true;                            
                        }
                    }                                  
                }
                if(newRun){
                    continue;
                }
                /*************************************************************/ 
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("Financial Crisis")){
                        gamecardrunner.applyCard(primary,user,another,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),0);
                        newRun=true;
                    }                                  
                }
                if(newRun){
                    continue;
                } 
                /*************************************************************/
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("Oil Shocks")){
                        gamecardrunner.applyCard(primary,user,another,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),0);
                        newRun=true;
                    }                                  
                }
                if(newRun){
                    continue;
                }       
                /*************************************************************/
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("Fire!")){
                        gamecardrunner.applyCard(primary,user,another,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),1);                        
                        newRun=true;
                    }                                  
                }   
                if(newRun){
                    continue;
                }   
                /*************************************************************/
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("Gig Economy")){
                        if(user.getBlood()<4){
                            gamecardrunner.applyCard(primary,user,another,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),0);
                            newRun=true;
                        }
                    }                                  
                }
                if(newRun){
                    continue;
                }        
                stop1=true;              
            }
        }
    }            
        }

        		/*****************************friend*********************************/
        else if(plusRoleNum%2==0){
            boolean stop=false;
            int notatt=0;
            while(!stop){
                if(chosen.getBlood()==0){
                    break;
                }
                /*************************************************************/
                boolean newRun=false;//this is use for restart  while loop
                /*************************************************************/
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("Please Support Counter")){
                        gamecardrunner.applyCard(primary,user,chosen,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),0);
                        newRun=true;
                    }                                  
                }
                if(newRun){
                    continue;
                }  
                /*************************************************************/  
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("Tonight I Want Some")){
                        gamecardrunner.applyCard(primary,user,chosen,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),0);
                        newRun=true;
                    }                                  
                }
                if(newRun){
                    continue;
                }
                /*************************************************************/
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("Different Pay For Equal Work")&&chosen.getBlood()<=2){
                        gamecardrunner.applyCard(primary,user,chosen,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),0);
                        newRun=true;
                    }                                  
                }
                if(newRun){
                    continue;
                }
                /*************************************************************/
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("Financial Crisis")){
                        gamecardrunner.applyCard(primary,user,chosen,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),0);
                        newRun=true;
                    }                                  
                }
                if(newRun){
                    continue;
                } 
                /*************************************************************/
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("Oil Shocks")){
                        gamecardrunner.applyCard(primary,user,chosen,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),0);
                        newRun=true;
                    }                                  
                }
                if(newRun){
                    continue;
                }      
                /*************************************************************/
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("Bar")){
                        if(user.getBlood()<3&&chosen.getBlood()<3){
                            gamecardrunner.applyCard(primary,user,chosen,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),0);
                        }
                    }                                  
                }      
                /*************************************************************/  
                for(int num=0;num<user.HandCardNum();num++){
                    if(card.getCardName(user.getHandCard().get(num)).equals("Gig Economy")){
                        if(user.getBlood()<4){
                            gamecardrunner.applyCard(primary,user,chosen,user.getHandCard().get(num),card.getCardName(user.getHandCard().get(num)),0);
                            newRun=true;
                        }
                    }                                  
                } 
                if(newRun){
                    continue;
                } 
                /*************************************************************/
                stop=true;              
            }
        } 













         
        /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/

    }
    public void auto_run(int roundPlayer, Primary primary){
        /*********************************The TODO This Time (Checkpoint3)*************************
         * 
         * TODO(1): Integrate use and fold funtion.
         * 
         * Requests:
         * 1. You must use both useCard(Player chosen,Primary primary) and fold() function in the method.
         * 
         * 
         * Hint:
         * 1. You can check who is your teammate by getRole() in Player.
         *    Even numbers are of the same team; Odd numbers are the other. 
         * 
         * 2. The Players you can get information are announced in Primary, where We provide method--getPlayer().
         *    Check out those method in Player.
         *    e.g. primary.getPlayer(roundPlayer).getBlood()
         * 
         * 3. you must input the player you choose and the primary class to usecard method, then use fold method.
         ***********************************The End of the TODO*************************************/
        /********************************************************************************************
         START OF YOUR CODE
         ********************************************************************************************/
				/**********************Player0************************/                
                if(roundPlayer==0){    
                    int player1role=primary.getPlayer(roundPlayer+1).getRole();
                    int player2role=primary.getPlayer(roundPlayer+2).getRole();
                    int player3role=primary.getPlayer(roundPlayer+3).getRole();

                    int plusplayer12role=player1role+player2role;
                    int plusplayer13role=player1role+player3role;
                    int plusplayer23role=player2role+player3role;

                    int player1blood=primary.getPlayer(roundPlayer+1).getBlood();
                    int player2blood=primary.getPlayer(roundPlayer+2).getBlood();
                    int player3blood=primary.getPlayer(roundPlayer+3).getBlood();

                    if((plusplayer12role%2)==0){
                        if((player1blood>=player2blood) && player2blood!=0){    
                            useCard(primary.getPlayer(roundPlayer+2),primary);//att enimy
                        }
                        else{
                            useCard(primary.getPlayer(roundPlayer+1),primary);   
                        }
                        if(player3blood!=0){
                            useCard(primary.getPlayer(roundPlayer+3),primary);
                        }
                        fold();
                    }                 
                    else if((plusplayer13role%2)==0){
                        if((player1blood>=player3blood) && player3blood!=0){    
                            useCard(primary.getPlayer(roundPlayer+3),primary);//att enimy
                        }
                        else{
                            useCard(primary.getPlayer(roundPlayer+1),primary);   
                        }
                        if(player2blood!=0){
                            useCard(primary.getPlayer(roundPlayer+2),primary);
                        }
                        fold();
                    }
                    else {  // waring!! here can't code else if ,it will make player0 do nothing in player0's run
                        if((player2blood>=player3blood) && player3blood!=0){    
                            useCard(primary.getPlayer(roundPlayer+3),primary);//att enimy
                        }
                        else{
                            useCard(primary.getPlayer(roundPlayer+2),primary);   
                        }
                        if(player1blood!=0){
                            useCard(primary.getPlayer(roundPlayer+1),primary);
                        }
                        fold();
                    }
                }
				/**********************Player1************************/
                if(roundPlayer==1){    
                    int player1role=primary.getPlayer(roundPlayer-1).getRole();
                    int player2role=primary.getPlayer(roundPlayer+1).getRole();
                    int player3role=primary.getPlayer(roundPlayer+2).getRole();

                    int plusplayer12role=player1role+player2role;
                    int plusplayer13role=player1role+player3role;
                    int plusplayer23role=player2role+player3role;

                    int player1blood=primary.getPlayer(roundPlayer-1).getBlood();
                    int player2blood=primary.getPlayer(roundPlayer+1).getBlood();
                    int player3blood=primary.getPlayer(roundPlayer+2).getBlood();

                    if((plusplayer12role%2)==0){
                        if((player1blood>=player2blood) && player2blood!=0){    
                            useCard(primary.getPlayer(roundPlayer+1),primary);//att enimy
                        }
                        else{
                            useCard(primary.getPlayer(roundPlayer-1),primary);   
                        }
                        if(player3blood!=0){
                            useCard(primary.getPlayer(roundPlayer+2),primary);
                        }
                        fold();
                    }                 
                    else if((plusplayer13role%2)==0){
                        if((player1blood>=player3blood) && player3blood!=0){    
                            useCard(primary.getPlayer(roundPlayer+2),primary);//att enimy
                        }
                        else{
                            useCard(primary.getPlayer(roundPlayer-1),primary);   
                        }
                        if(player2blood!=0){
                            useCard(primary.getPlayer(roundPlayer+1),primary);
                        }
                        fold();
                    }
                    else {  // waring!! here can't code else if ,it will make player0 do nothing in player0's run
                        if((player2blood>=player3blood) && player3blood!=0){    
                            useCard(primary.getPlayer(roundPlayer+2),primary);//att enimy
                        }
                        else{
                            useCard(primary.getPlayer(roundPlayer+1),primary);   
                        }
                        if(player1blood!=0){
                            useCard(primary.getPlayer(roundPlayer-1),primary);
                        }
                        fold();
                    }
                }
				/**********************Player2************************/
                if(roundPlayer==2){    
                    int player1role=primary.getPlayer(roundPlayer-2).getRole();
                    int player2role=primary.getPlayer(roundPlayer-1).getRole();
                    int player3role=primary.getPlayer(roundPlayer+1).getRole();

                    int plusplayer12role=player1role+player2role;
                    int plusplayer13role=player1role+player3role;
                    int plusplayer23role=player2role+player3role;

                    int player1blood=primary.getPlayer(roundPlayer-2).getBlood();
                    int player2blood=primary.getPlayer(roundPlayer-1).getBlood();
                    int player3blood=primary.getPlayer(roundPlayer+1).getBlood();

                    if((plusplayer12role%2)==0){
                        if((player1blood>=player2blood) && player2blood!=0){    
                            useCard(primary.getPlayer(roundPlayer-1),primary);//att enimy
                        }
                        else{
                            useCard(primary.getPlayer(roundPlayer-2),primary);   
                        }
                        if(player3blood!=0){
                            useCard(primary.getPlayer(roundPlayer+1),primary);
                        }
                        fold();
                    }                 
                    else if((plusplayer13role%2)==0){
                        if((player1blood>=player3blood) && player3blood!=0){    
                            useCard(primary.getPlayer(roundPlayer+1),primary);//att enimy
                        }
                        else{
                            useCard(primary.getPlayer(roundPlayer-2),primary);   
                        }
                        if(player2blood!=0){
                            useCard(primary.getPlayer(roundPlayer-1),primary);
                        }
                        fold();
                    }
                    else {  // waring!! here can't code else if ,it will make player0 do nothing in player0's run
                        if((player2blood>=player3blood) && player3blood!=0){    
                            useCard(primary.getPlayer(roundPlayer+1),primary);//att enimy
                        }
                        else{
                            useCard(primary.getPlayer(roundPlayer-1),primary);   
                        }
                        if(player1blood!=0){
                            useCard(primary.getPlayer(roundPlayer-2),primary);
                        }
                        fold();
                    }
                }
				/**********************Player3************************/								
                if(roundPlayer==3){    
                    int player1role=primary.getPlayer(roundPlayer-3).getRole();
                    int player2role=primary.getPlayer(roundPlayer-2).getRole();
                    int player3role=primary.getPlayer(roundPlayer-1).getRole();

                    int plusplayer12role=player1role+player2role;
                    int plusplayer13role=player1role+player3role;
                    int plusplayer23role=player2role+player3role;

                    int player1blood=primary.getPlayer(roundPlayer-3).getBlood();
                    int player2blood=primary.getPlayer(roundPlayer-2).getBlood();
                    int player3blood=primary.getPlayer(roundPlayer-1).getBlood();

                    if((plusplayer12role%2)==0){
                        if((player1blood>=player2blood) && player2blood!=0){    
                            useCard(primary.getPlayer(roundPlayer-2),primary);//att enimy
                        }
                        else{
                            useCard(primary.getPlayer(roundPlayer-3),primary);   
                        }
                        if(player3blood!=0){
                            useCard(primary.getPlayer(roundPlayer-1),primary);
                        }
                        fold();
                    }                 
                    else if((plusplayer13role%2)==0){
                        if((player1blood>=player3blood) && player3blood!=0){    
                            useCard(primary.getPlayer(roundPlayer-1),primary);//att enimy
                        }
                        else{
                            useCard(primary.getPlayer(roundPlayer-3),primary);   
                        }
                        if(player2blood!=0){
                            useCard(primary.getPlayer(roundPlayer-2),primary);
                        }
                        fold();
                    }
                    else {  // waring!! here can't code else if ,it will make player0 do nothing in player0's run
                        if((player2blood>=player3blood) && player3blood!=0){    
                            useCard(primary.getPlayer(roundPlayer-1),primary);//att enimy
                        }
                        else{
                            useCard(primary.getPlayer(roundPlayer-2),primary);   
                        }
                        if(player1blood!=0){
                            useCard(primary.getPlayer(roundPlayer-3),primary);
                        }
                        fold();
                    }
                }

         
        /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/
    }
}
