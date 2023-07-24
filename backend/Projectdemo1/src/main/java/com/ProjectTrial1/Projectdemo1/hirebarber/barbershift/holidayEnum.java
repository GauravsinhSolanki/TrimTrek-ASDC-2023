package com.ProjectTrial1.Projectdemo1.hirebarber.barbershift;

public enum holidayEnum {


    UTTRAYAN("Uttrayan"),
    REPUBLICDAY("Republic Day"),
    MAHASHIVRATRI("Maha Shivratri"),
    HOLI("Holi"),
    BUDDHAJAYANTI("Buddha Jayanti"),
    RATHAYATRA("Ratha Yatra"),
    RAKSHABANDHAN("Rakshabandhan"),
    JANMASHTMI("Janmashtmi"),
    INDEPENDENCEDAY("Independence Day"),
    GANESHCHATURTHI("Ganesh Chaturthi"),
    NAVRATRI("Navratri"),
    DURGAPOOJA("Durga Puja"),
    DUSSEHRA("Dussehra"),
    DIWALI("Diwali"),
    CHRISTMAS("Christmas"),

    ;


    private final String holidayName;
    holidayEnum(String holidayName) {
        this.holidayName = holidayName;
    }

    public String getHolidayName() {
        return this.holidayName;
    }






}
