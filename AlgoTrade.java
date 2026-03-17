import java.util.*;

interface TradingStrategy {
    boolean shouldExecuteTrade(List<Double> priceHistory);
}

class TrendFollowerStrategy implements TradingStrategy {
    @Override
    public boolean shouldExecuteTrade(List<Double> priceHistory) {
        if (priceHistory.size() < 4) return false;
        
        int lastIndex = priceHistory.size() - 1;
        double latest = priceHistory.get(lastIndex);
        
        return latest > priceHistory.get(lastIndex - 1) &&
               latest > priceHistory.get(lastIndex - 2) &&
               latest > priceHistory.get(lastIndex - 3);
    }
}

class ContrarianStrategy implements TradingStrategy {
    @Override
    public boolean shouldExecuteTrade(List<Double> priceHistory) {
        if (priceHistory.size() < 4) return false;
        
        int lastIndex = priceHistory.size() - 1;
        double latest = priceHistory.get(lastIndex);
        
        return latest < priceHistory.get(lastIndex - 1) &&
               latest < priceHistory.get(lastIndex - 2) &&
               latest < priceHistory.get(lastIndex - 3);
    }
}

class PanicDropStrategy implements TradingStrategy {
    @Override
    public boolean shouldExecuteTrade(List<Double> priceHistory) {
        if (priceHistory.size() < 2) return false;
        
        double latest = priceHistory.get(priceHistory.size() - 1);
        double first = priceHistory.get(0);
        
        return latest < first * 0.90;
    }
}

class StrategyRegistry {
    private static final Map<String, TradingStrategy> REGISTRY = new HashMap<>();
    
    static {
        REGISTRY.put("TREND", new TrendFollowerStrategy());
        REGISTRY.put("CONTRARIAN", new ContrarianStrategy());
        REGISTRY.put("PANIC", new PanicDropStrategy());
    }
    
    public static TradingStrategy getStrategy(String strategyKey) {
        TradingStrategy strategy = REGISTRY.get(strategyKey.toUpperCase());
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown strategy: " + strategyKey);
        }
        return strategy;
    }
    
    public static void register(String key, TradingStrategy strategy) {
        REGISTRY.put(key.toUpperCase(), strategy);
    }
}

class TradingBot {
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

public class AlgoTrade {
    public static void main(String[] args) {
        TradingBot bot = new TradingBot("CONTRARIAN");
        
        System.out.println("=== Phase 1: CONTRARIAN Strategy ===");
        
        double[] phase1Prices = {150.0, 148.0, 145.0, 142.0};
        for (double price : phase1Prices) {
            bot.addPrice(price);
            System.out.print("Price added: " + price + " -> ");
            bot.evaluate();
        }
        
        System.out.println("\n=== Phase 2: Switch to TREND Strategy ===");
        
        bot.setStrategy("TREND");
        
        double[] phase2Prices = {144.0, 146.0, 149.0, 155.0};
        for (double price : phase2Prices) {
            bot.addPrice(price);
            System.out.print("Price added: " + price + " -> ");
            bot.evaluate();
        }
    }
}