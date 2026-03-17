import java.util.ArrayList;
import java.util.List;

public class TradingBot {
    private final List<Double> priceHistory = new ArrayList<>();
    private TradingStrategy currentStrategy;
    
    public TradingBot(String initialStrategyKey) {
        this.currentStrategy = StrategyRegistry.getStrategy(initialStrategyKey);
    }
    
    public void setStrategy(String strategyKey) {
        this.currentStrategy = StrategyRegistry.getStrategy(strategyKey);
        System.out.println("[Strategy switched to: " + strategyKey + "]");
    }
    
    public void addPrice(double price) {
        priceHistory.add(price);
    }
    
    public void evaluate() {
        if (currentStrategy.shouldExecuteTrade(priceHistory)) {
            double latestPrice = priceHistory.get(priceHistory.size() - 1);
            System.out.println("Trade Executed at price: " + latestPrice);
        }
    }
}