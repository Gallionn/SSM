package demo.ssm.pojo;

public class TCKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tc.c_id
     *
     * @mbggenerated Sat Nov 26 00:22:11 CST 2022
     */
    private String cId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tc.t_no
     *
     * @mbggenerated Sat Nov 26 00:22:11 CST 2022
     */
    private String tNo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc.c_id
     *
     * @return the value of tc.c_id
     *
     * @mbggenerated Sat Nov 26 00:22:11 CST 2022
     */
    public String getcId() {
        return cId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc.c_id
     *
     * @param cId the value for tc.c_id
     *
     * @mbggenerated Sat Nov 26 00:22:11 CST 2022
     */
    public void setcId(String cId) {
        this.cId = cId == null ? null : cId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc.t_no
     *
     * @return the value of tc.t_no
     *
     * @mbggenerated Sat Nov 26 00:22:11 CST 2022
     */
    public String gettNo() {
        return tNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc.t_no
     *
     * @param tNo the value for tc.t_no
     *
     * @mbggenerated Sat Nov 26 00:22:11 CST 2022
     */
    public void settNo(String tNo) {
        this.tNo = tNo == null ? null : tNo.trim();
    }
}