package janjira.jiraporn.yonlada.aroirestuarant.utility;

/**
 * Created by ASUS on 10/16/2017.
 */

public class MyConstanct {

    //    URL
    private String urlEditStatusByChef = "http://androidthai.in.th/mua/editStatusByChef.php";
    private String urlGetOrderWhereDate = "http://androidthai.in.th/mua/getOrderWhereDate.php";
    private String urlEditTable = "http://androidthai.in.th/mua/editTable.php";
    private String urlGetFoodWhereName = "http://androidthai.in.th/mua/getFoodWhereName.php";
    private String urlGetOrderWhereDateAnOrderID = "http://androidthai.in.th/mua/getOrderWhereDateAnOrderID.php";
    private String urlAddOrder = "http://androidthai.in.th/mua/addOrder.php";
    private String urlPromotionString = "http://androidthai.in.th/mua/getAllData.php";
    private String urlAddUserString = "http://androidthai.in.th/mua/addDataMaster.php";
    private String urlGetAllUserString = "http://androidthai.in.th/mua/getAllUser.php";


    //    Array

    private String[] columnOrder = new String[]{
            "id",
            "orderDate",
            "orderID",
            "orNameFood",
            "Item",
            "Status"};

    private String[] columnFood = new String[]{
            "id",
            "Category",
            "NameFood",
            "Price",
            "Detail",
            "ImagePath",
            "NameReview",
            "DetailReview",
            "ScoreReview"};

    private String[] categoryStrings = new String[]{
            "โปรโมชั่น",
            "อาหารจานด่วน",
            "แกง",
            "ปิ้ง/ย่าง/ทอด",
            "อาหารชุด",
            "เส้น",
            "นำ้พริก",
            "นึ่ง", "อาหารว่าง/ขนม", "ผลไม้", "เครื่องดื่ม"};

    //    Method


    public String getUrlEditStatusByChef() {
        return urlEditStatusByChef;
    }

    public String getUrlGetOrderWhereDate() {
        return urlGetOrderWhereDate;
    }

    public String getUrlEditTable() {
        return urlEditTable;
    }

    public String getUrlGetFoodWhereName() {
        return urlGetFoodWhereName;
    }

    public String getUrlGetOrderWhereDateAnOrderID() {
        return urlGetOrderWhereDateAnOrderID;
    }

    public String[] getColumnOrder() {
        return columnOrder;
    }

    public String getUrlAddOrder() {
        return urlAddOrder;
    }

    public String getUrlGetAllUserString() {
        return urlGetAllUserString;
    }

    public String getUrlAddUserString() {
        return urlAddUserString;
    }

    public String[] getCategoryStrings() {
        return categoryStrings;
    }

    public String[] getColumnFood() {
        return columnFood;
    }

    public String getUrlPromotionString() {
        return urlPromotionString;
    }
}   // Main Class
