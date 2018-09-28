package sample;

public class ModelTable {
    String name;
    String subject;
    String description1;
    String email ;
    String number;
    String sms;
    Integer status ;
    Integer freq;

    public ModelTable(String name, String subject, String description1, String email, String number, String sms, Integer status, Integer freq) {
        this.name = name;
        this.subject = subject;
        this.description1 = description1;
        this.email = email;
        this.number = number;
        this.sms = sms;
        this.status = status;
        this.freq = freq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFreq() {
        return freq;
    }

    public void setFreq(Integer freq) {
        this.freq = freq;
    }
}
