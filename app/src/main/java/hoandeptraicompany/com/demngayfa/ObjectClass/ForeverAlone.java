package hoandeptraicompany.com.demngayfa.ObjectClass;

import android.text.LoginFilter;
import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ForeverAlone implements Serializable {
    private int dayFA;
    private int monthFA;
    private int yearFA;
    public int countDayFA;
    private int countWeekDayFA;
    private int countMonthFA;
    private int countYearFA;
    private int countDayFAKE;
    public Date dateFA;

    public ForeverAlone(int dayFA, int monthFA, int yearFA) {
        this.dayFA = dayFA;
        this.monthFA = monthFA;
        this.yearFA = yearFA;
        countWeekDayFA = 0;
        countMonthFA = 0;
        countYearFA = 0;
        Calendar cal = Calendar.getInstance();
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);
        int currentMonth = cal.get(Calendar.MONTH);
        int currentYear = cal.get(Calendar.YEAR);

        SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
        String stringDate1 = currentDay + " " + currentMonth + " " + currentYear;
        String stringDate2 = dayFA + " " + monthFA + " " + yearFA;

        try {
            Date date1 = format.parse(stringDate1);
            Date date2 = format.parse(stringDate2);
            long diff = date1.getTime() - date2.getTime();
            countDayFA = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            this.countDayFAKE = this.countDayFA;

            countYearFA = countDayFA / 365;
            countMonthFA = (countDayFA - countYearFA * 365) / 30;
            countWeekDayFA = (countDayFA - countYearFA * 365 - countMonthFA * 30) / 7;
            countDayFAKE = countDayFA - countYearFA * 365 - countMonthFA * 30 - countWeekDayFA * 7;
            Log.d("kiemtra", "dayfake:" + countDayFAKE + ",week:" + countWeekDayFA + ",month:" + countMonthFA + ",year:" + countYearFA + ",daycount:" + countDayFA);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void setCountDayFA(int countDayFA) {
        this.countDayFA = countDayFA;
    }

    public int getCountWeekDayFA() {
        return countWeekDayFA;
    }

    public void setCountWeekDayFA(int countWeekDayFA) {
        this.countWeekDayFA = countWeekDayFA;
    }

    public int getCountMonthFA() {
        return countMonthFA;
    }

    public void setCountMonthFA(int countMonthFA) {
        this.countMonthFA = countMonthFA;
    }

    public int getCountYearFA() {
        return countYearFA;
    }

    public void setCountYearFA(int countYearFA) {
        this.countYearFA = countYearFA;
    }

    public int getCountDayFAKE() {
        return countDayFAKE;
    }

    public void setCountDayFAKE(int countDayFAKE) {
        this.countDayFAKE = countDayFAKE;
    }

    public void setDateFA(Date dateFA) {
        this.dateFA = dateFA;
    }

    public int getDayFA() {
        return this.dayFA;
    }

    public int getMonthFA() {
        return this.monthFA;
    }

    public int getYearFA() {
        return this.yearFA;
    }

    public int getCountDayFA() {
        return this.countDayFA;
    }

    public Date getDateFA() {
        return this.dateFA;
    }

    public void setDayFA(int dayFA) {
        this.dayFA = dayFA;
    }

    public void setMonthFA(int monthFA) {
        this.monthFA = monthFA;
    }

    public void setYearFA(int yearFA) {
        this.yearFA = yearFA;
    }

    public void faChange() {
        Calendar cal = Calendar.getInstance();
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);
        int currentMonth = cal.get(Calendar.MONTH);
        int currentYear = cal.get(Calendar.YEAR);

        cal.set(currentYear, currentMonth, currentDay);
        Date currentDate = cal.getTime();

        cal.set(yearFA, monthFA, dayFA);
        this.dateFA = cal.getTime();

        long diff = currentDate.getTime() - dateFA.getTime();
        this.countDayFA = (int) diff / (86400000);
        Log.d("kiemtrasongayfa", countDayFA + "");

    }

}