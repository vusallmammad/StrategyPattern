import java.util.List;

public class PanicDropStrategy implements TradingStrategy {
    @Override
    public boolean shouldExecuteTrade(List<Double> priceHistory) {
        if (priceHistory.size() < 2) return false;
        
        double latest = priceHistory.get(priceHistory.size() - 1);
        double first = priceHistory.get(0);
        
        return latest < first * 0.90;
    }
}