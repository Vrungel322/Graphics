
package nanddgroup.graphics.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("profit")
    @Expose
    private String profit;
    @SerializedName("profit_month_money")
    @Expose
    private String profitMonthMoney;
    @SerializedName("total_profit")
    @Expose
    private String totalProfit;
    @SerializedName("profit_month")
    @Expose
    private String profitMonth;
    @SerializedName("date")
    @Expose
    private String date;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Item() {
    }

    /**
     * 
     * @param profitMonthMoney
     * @param profit
     * @param profitMonth
     * @param totalProfit
     * @param date
     */
    public Item(String profit, String profitMonthMoney, String totalProfit, String profitMonth, String date) {
        this.profit = profit;
        this.profitMonthMoney = profitMonthMoney;
        this.totalProfit = totalProfit;
        this.profitMonth = profitMonth;
        this.date = date;
    }

    /**
     * 
     * @return
     *     The profit
     */
    public String getProfit() {
        return profit;
    }

    /**
     * 
     * @param profit
     *     The profit
     */
    public void setProfit(String profit) {
        this.profit = profit;
    }

    /**
     * 
     * @return
     *     The profitMonthMoney
     */
    public String getProfitMonthMoney() {
        return profitMonthMoney;
    }

    /**
     * 
     * @param profitMonthMoney
     *     The profit_month_money
     */
    public void setProfitMonthMoney(String profitMonthMoney) {
        this.profitMonthMoney = profitMonthMoney;
    }

    /**
     * 
     * @return
     *     The totalProfit
     */
    public String getTotalProfit() {
        return totalProfit;
    }

    /**
     * 
     * @param totalProfit
     *     The total_profit
     */
    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    /**
     * 
     * @return
     *     The profitMonth
     */
    public String getProfitMonth() {
        return profitMonth;
    }

    /**
     * 
     * @param profitMonth
     *     The profit_month
     */
    public void setProfitMonth(String profitMonth) {
        this.profitMonth = profitMonth;
    }

    /**
     * 
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The date
     */
    public void setDate(String date) {
        this.date = date;
    }

}
