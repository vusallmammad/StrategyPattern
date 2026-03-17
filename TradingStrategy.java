import java.util.List;

public interface TradingStrategy {
    boolean shouldExecuteTrade(List<Double> priceHistory);
}