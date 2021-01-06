package aralasu.kz.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class User {
    private Long id;
    private String email;
    private String password;
    private String full_name;
    private String birth_date;
    private String picture_url;

    public User(Long id, String email, String password, String full_name, String birth_date, String picture_url) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.full_name = full_name;
        this.birth_date = birth_date;
        this.picture_url = picture_url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public int getAge()  {
        String s = getBirth_date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date d = null;
        try {
            d = sdf.parse(s);
        }catch (ParseException p){
            p.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH) + 1;
        int dd = c.get(Calendar.DATE);
        LocalDate l = LocalDate.of(y,m,dd);
        LocalDate now = LocalDate.now();
        Period diff = Period.between(l,now);
        return diff.getYears();
    }
}
