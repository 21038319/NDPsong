package sg.edu.rp.c346.id21038319.ndpsong;

import java.io.Serializable;

public class Songs implements Serializable {
    private int _id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Songs( int id,String title, String singers, int year, int stars ) {
        this._id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public Songs(int year) {
        this.year = year;
    }

    public int get_id() {  return _id;  }

    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSingers(String singers) {
        this.singers = singers;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getYear() {
        return year;
    }

    public int getStars() {
        return stars;
    }

    @Override
    public String toString() {
        String year = Integer.toString(getYear());
        String starsEmo = "";
        for(int i = 0;i < stars;i++){
            starsEmo = starsEmo + "✩";
        }
        String display = "";
        if (title == null){
            display = year;
        }else{
            display = year + "  -  " +  starsEmo;

        }
        return display;
    }


}
