//define strategy Interface
public interface StrategyInterface{
    void fold();
    void useCard(Player chosen,Primary primary);
    void auto_run(int roundPlayer, Primary primary);   
}
