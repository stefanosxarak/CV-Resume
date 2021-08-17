package uobspe.stonks;

public class Recipt {

    final private String owner;
    final private String stockln;
    final private int numberOfStockInvolved;
    final private float valueAtTransaction;
    final private boolean wasPurchase;

    public Recipt(String owner, String stockln, int numberPurchased, float valueAtPurchase, boolean wasPurchase) {
        this.owner = owner;
        this.stockln = stockln;
        this.numberOfStockInvolved = numberPurchased;
        this.valueAtTransaction = valueAtPurchase;
        this.wasPurchase = wasPurchase;
    }

    public int getNumberInvolved(){
        return numberOfStockInvolved;
    }

    public float getValueAtTransaction() {
        return valueAtTransaction;
    }

    public String getStockln() {
        return stockln;
    }

    public String getOwner() {
        return owner;
    }

    public Boolean wasTransationAPurchase(){return wasPurchase;}

    public String getAsHtmlTableRow(){
        String toReturn = "<tr>";

        toReturn += "<td>" + getOwner() + "</td>";
        toReturn += "<td>" + stockln + "</td>";
        toReturn += "<td>" + String.valueOf(numberOfStockInvolved) + "</td>";
        toReturn += "<td>" + (wasPurchase ? "Purchase" : "Sale") + "</td>";
        toReturn += "<td>" + String.valueOf(valueAtTransaction) + "</td>";

        return toReturn + "</tr>";
    }
}