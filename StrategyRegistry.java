import java.util.HashMap;
import java.util.Map;

public class StrategyRegistry {
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