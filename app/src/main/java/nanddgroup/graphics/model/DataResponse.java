
package nanddgroup.graphics.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataResponse {

    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<Item>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public DataResponse() {
    }

    /**
     * 
     * @param total
     * @param items
     */
    public DataResponse(int total, List<Item> items) {
        this.total = total;
        this.items = items;
    }

    /**
     * 
     * @return
     *     The total
     */
    public int getTotal() {
        return total;
    }

    /**
     * 
     * @param total
     *     The total
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * 
     * @return
     *     The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * 
     * @param items
     *     The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

}
