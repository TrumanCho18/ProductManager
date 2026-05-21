import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.DAYS;

public class Product {
    private String Name;

    private double Cost;
    private double SellPrice;
    private double SubProfit;

    private int MonthListed;
    private int DayListed;
    private int YearListed;

    private int MonthSold = -1;
    private int DaySold = -1;
    private int YearSold = -1;

    private int Qty;
    private int SoldAmt;

    private double Efficiency;

    //CONSTRUCTOR
    public Product(String n, double Lp, double Sp, int M, int D, int Y) {
        Name = n;
        Cost = Lp;
        SellPrice = Sp;
        SubProfit = SellPrice - Cost;
        MonthListed = M;
        DayListed = D;
        YearListed = Y;
        Qty = 1;
    }

    @Override
    public String toString() {

        return "+----------------------------+ \n Name: " + Name + "\n SellPrice: " + SellPrice + "\n SubProfit: " + SubProfit + "\n DateListed: " + getDateListed() + "\n AvgEfficiency: " + Efficiency + "\n Quantity: " + Qty + "\n AmountSold: " + SoldAmt;

    }

    public void setEfficiency() {
        Qty--;
        SoldAmt++;
        LocalDate List = LocalDate.of(YearListed, MonthListed, DayListed);
        LocalDate Sold = LocalDate.of(YearSold, MonthSold, DaySold);
        long noOfDaysBetween = DAYS.between(List, Sold);

        double ans = SubProfit / noOfDaysBetween;

        Efficiency = (Efficiency + ans) / (SoldAmt);
    }

    public void AddStock(int amt) {
        Qty += amt;
        //System.out.println("Quantity of " + Name + " successfully updated.");
        //System.out.println(Name + " qty: " + Qty);
    }

    //GETTERS
    public String getName() {
        return Name;
    }

    public double getCost() {
        return Cost;
    }

    public double getSellPrice() {
        return SellPrice;
    }

    public double getSubProfit() {
        return SubProfit;
    }

    public int getMonthListed() {
        return MonthListed;
    }

    public int getDayListed() {
        return DayListed;
    }

    public int getYearListed() {
        return YearListed;
    }

    public String getDateListed() {
        return MonthListed + "/" + DayListed + "/" + YearListed;
    }

    public int getMonthSold() {
        return MonthSold;
    }

    public int getDaySold() {
        return DaySold;
    }

    public int getYearSold() {
        return YearSold;
    }

    //SETTERS
    public void setName(String name) {
        Name = name;
    }

    public void setCost(double cost) {
        Cost = cost;
    }

    public void setSellPrice(double sellPrice) {
        SellPrice = sellPrice;
    }

    public void setSubProfit(double subProfit) {
        SubProfit = subProfit;
    }

    public void setMonthListed(int monthListed) {
        MonthListed = monthListed;
    }

    public void setDayListed(int dayListed) {
        DayListed = dayListed;
    }

    public void setYearListed(int yearListed) {
        YearListed = yearListed;
    }

    public void setMonthSold(int monthSold) {
        MonthSold = monthSold;
    }

    public void setDaySold(int daySold) {
        DaySold = daySold;
    }

    public void setYearSold(int yearSold) {
        YearSold = yearSold;
    }
}
