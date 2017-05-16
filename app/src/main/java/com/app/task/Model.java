package com.app.task;

import java.io.Serializable;
import java.util.ArrayList;

public class Model implements Serializable {
    public ArrayList<User> users;

    public class User implements Serializable{
        String name, email;
        Address address;
        String phone,website;
        public class Address implements Serializable{
            String street;
            String city;
            String zipcode;
            Geo geo;

            public class Geo implements Serializable{
                String lat, lng;
            }

        }
    }
}