
package by.slowar.rentchecker.data.items.onliner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("converted")
    @Expose
    private Converted converted;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Converted getConverted() {
        return converted;
    }

    public void setConverted(Converted converted) {
        this.converted = converted;
    }

}
