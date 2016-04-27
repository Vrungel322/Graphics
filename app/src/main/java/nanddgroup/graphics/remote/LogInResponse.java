
package nanddgroup.graphics.remote;

public class LogInResponse {


    private String lang;
    private Boolean success;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LogInResponse() {
    }

    /**
     * 
     * @param success
     * @param lang
     */
    public LogInResponse(String lang, Boolean success) {
        this.lang = lang;
        this.success = success;
    }

    /**
     * 
     * @return
     *     The lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * 
     * @param lang
     *     The lang
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * 
     * @return
     *     The success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * 
     * @param success
     *     The success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
