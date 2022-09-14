public class Card{
    private int cardId;
    public Card(int cardId){
        super();
        this.cardId=cardId;
    }
    // Through the card id, it will return the card name by invoking this method.
    public String getCardName(int cardId){
        String s="";
        switch(cardId) {
            case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19: case 20: case 21: case 22: case 23: case 24: case 25: case 64: case 65: case 66: case 63:
                s="Fire!";
                break;
            case 26: case 27: case 28: case 29: case 30: case 31: case 32: case 33: case 34: case 35: case 36: case 37: case 38: case 39: case 67: case 68: case 69: case 70: case 79:
                s="Miss"; 
                break;
            case 44: case 45: case 46: case 47: case 73: case 40:
                s="beibigennosuke";
                break;
            case 48: case 49: case 50: case 51: case 74: case 41:
                s="awpjopabemarrrr";
                break;
            case 52: case 53: case 75: case 71:
                s="Tonight I Want Some";
                break;
            case 54: case 76: case 42:
                s="Please Support Counter";
                break;
            case 55: case 56: case 77: case 43:
                s="Different Pay For Equal Work";
                break;
            case 57: case 58: case 59: case 60: case 61: case 62: 
                s="Gig Economy";
                break;
            case 78: case 72:
                s="Bar";
                break;
        }
        return(s);
    }
    
    public String getCardIntroduction(String cardName){
        String cardIntroduction="";
        switch(cardName) {
            case "Fire!":
                cardIntroduction="<html> 攻擊一名玩家一次<html/>";
                break;
            case "Miss":
                cardIntroduction="<html>抵銷一名玩家的攻擊<html/>";
                break;
            case "beibigennosuke":
                cardIntroduction="<html>選一名玩家抽一張其手牌<html/>";
                break;
            case "awpjopabemarrrr":
                cardIntroduction="<html>指定一名玩家棄一張牌<html/>";
                break;
            case "Tonight I Want Some":
                cardIntroduction="<html>自己抽兩張牌<html/>";
                break;
            case "Please Support Counter":
                cardIntroduction="<html>自己抽三張牌<html/>";
                break;
            case "Different Pay For Equal Work":
                cardIntroduction="<html>倖存玩家依序抽取一張牌<html/>";
                break;
            case "Gig Economy":
                cardIntroduction="<html>自己加一滴血<html/>";
                break;
        }
        return cardIntroduction;
    }

    // return this id of card.
    public int getCardId(){
        return this.cardId;
    }
}
