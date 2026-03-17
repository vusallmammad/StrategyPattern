import java.util.List;

public class ContrarianStrategy implements TradingStrategy {
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