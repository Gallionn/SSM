package demo.ssm.pojo;

public class schedule {
    private String sNo;
    private String cId;
    private Integer grade;
    private String scStatus;
    private String info;
    private String cNo;
    private String tNo;
    private String time;
    private String tcStatus;
    private String cName;
    private String cPno;
    private Integer cCredit;

    public schedule() {
    }

    public schedule(String sNo, String cId, Integer grade, String scStatus, String info, String cNo, String tNo, String time, String tcStatus, String cName, String cPno, Integer cCredit) {
        this.sNo = sNo;
        this.cId = cId;
        this.grade = grade;
        this.scStatus = scStatus;
        this.info = info;
        this.cNo = cNo;
        this.tNo = tNo;
        this.time = time;
        this.tcStatus = tcStatus;
        this.cName = cName;
        this.cPno = cPno;
        this.cCredit = cCredit;
    }

    @Override
    public String toString() {
        return "schedule{" +
                "sNo='" + sNo + '\'' +
                ", cId='" + cId + '\'' +
                ", grade=" + grade +
                ", scStatus='" + scStatus + '\'' +
                ", info='" + info + '\'' +
                ", cNo='" + cNo + '\'' +
                ", tNo='" + tNo + '\'' +
                ", time='" + time + '\'' +
                ", tcStatus='" + tcStatus + '\'' +
                ", cName='" + cName + '\'' +
                ", cPno='" + cPno + '\'' +
                ", cCredit=" + cCredit +
                '}';
    }

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getScStatus() {
        return scStatus;
    }

    public void setScStatus(String scStatus) {
        this.scStatus = scStatus;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getcNo() {
        return cNo;
    }

    public void setcNo(String cNo) {
        this.cNo = cNo;
    }

    public String gettNo() {
        return tNo;
    }

    public void settNo(String tNo) {
        this.tNo = tNo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTcStatus() {
        return tcStatus;
    }

    public void setTcStatus(String tcStatus) {
        this.tcStatus = tcStatus;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcPno() {
        return cPno;
    }

    public void setcPno(String cPno) {
        this.cPno = cPno;
    }

    public Integer getcCredit() {
        return cCredit;
    }

    public void setcCredit(Integer cCredit) {
        this.cCredit = cCredit;
    }
}
