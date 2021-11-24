package com.company.model.dao;

public class SQL {
    private SQL(){}

    public static final String INSERT_USER = "insert into " +
            "account(login,password,role_id,account_details_id) " +
            "values(?,?,(select id from role where role = ?),?);";
    public static final String INSERT_USER_DETAILS = "insert into "+
            "account_details(first_name_en,last_name_en,first_name_ua,last_name_ua,email,phone) " +
            "values(?,?,?,?,?,?);";
    public static final String SELECT_USER = "select * from account where login = ?;";
    public static final String SELECT_USER_DETAILS = "select * from account_details where id = ?;";


    public static final String INSERT_TARIFF = "insert into "+
            "tariff(name_en,time,description,price,service_id,place_id,tariff_status_id) "+
            "values(?,?,?,?,?,?,?);";
    public static final String  SELECT_TARIFF_BY_ID = "select * from tariff where id = ?;";

    public static final String SELECT_TARIFFS = "select * from tariff;";

    public static final String INSERT_PLACE = "insert into " +
            "place(street,district,city) " + "values(?,?,?);";
    public static final String SELECT_ROLE = "select role from role where id = ?;";

    public static final String SELECT_PLACE_BY_ID = "select * from place where id = ?;";

    public static final String INSERT_WALLET = "insert into " +
            "wallet(funds,account_id) " + "values(?,?);";

    public static final String INSERT_PAYMENT= "insert into payment(wallet_id,tariff_id,price) " +
            "values(?,?,?);";

    public static final String SELECT_WALLET_BY_ID = "select * from wallet where account_id = ?;";

    public static final String SELECT_TARIFF_STATUS_BY_ID = "select * from tariff_status where id = ?;";

    public static final String INSERT_TARIFF_TO_ORDER = "insert into " +
            "tariff_to_order(name_en,description,tariff_status_id,account_id) "
            + "values(?,?,?,(select id from tariff_status where status_en = 'available'));";

    public static final String GET_TARIFF_TO_ORDER = "select * from tariff_to_order where id = ?;";

    public static final String SELECT_USER_PAYMENT = "select * from payment where wallet_id = ?;";

    public static final String SELECT_USER_BY_ID = "select * from account where id = ?;";

    public static final String MIN_PRICE_TARIFF = "select min(price) from service;";

    public static final String MAX_PRICE_TARIFF = "select max(price) from service;";

    public static final String SELECT_TARIFF_LIMITS = "select * from payment where account_id = ? limit ?,?;";

    public static final String SELECT_TARIFF_STATUS = "select * from tariff_status;";

    public static final String SELECT_TARIFF_BY_PLACE = "select * from tariff where place_id = ?;";

    public static final String SELECT_TARIFF_BY_SERVICE = "select * from service where service_id = (select id from service where service = ?);";

    public static final String SET_TARIFF_STATUS = "update tariff set tariff_status_id = ? where id = ?;";

    public static final String SELECT_SERVICE = "select * from service where id = ?;";


}
