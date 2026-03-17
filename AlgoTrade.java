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