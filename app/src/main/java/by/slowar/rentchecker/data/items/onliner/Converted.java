
package by.slowar.rentchecker.data.items.onliner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Converted {

    @SerializedName("BYN")
    @Expose
    private BYN bYN;
    @SerializedName("USD")
    @Expose
    private USD uSD;

    public BYN getBYN() {
        return bYN;
    }

    public void setBYN(BYN bYN) {
        this.bYN = bYN;
    }

    public USD getUSD() {
        return uSD;
    }

    public void setUSD(USD uSD) {
        this.uSD = uSD;
    }

}
