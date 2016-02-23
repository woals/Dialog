package com.example.yinyxn.dialog;

import android.os.Build;

/**
 * Created by yinyxn on 2015/11/19.
 */
public class Person {
    private String name;
    private int age;
    private String gender;
    private String phone;
    private String email;
    private String address;

    private Person(Builder builder) {
        name = builder.name;
        phone = builder.phone;
        email = builder.email;
        gender = builder.gender;
        address = builder.address;
        age = builder.age;
    }

    public static class Builder {

        private String name;
        private int age;
        private String gender;
        private String phone;
        private String email;
        private String address;

        public Builder(String name) {
            this.name = name;
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }
        public Builder setAge(int age){
            this.age = age;
            return this;
        }

        public Builder setGender(String gender){
            this.gender = gender;
            return this;
        }
        public Builder setPhone(String phone){
            this.phone = phone;
            return this;
        }
        public Builder setEmail(String email){
            this.email = email;
            return this;
        }
        public Builder setAddress(String address){
            this.address = address;
            return this;
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", gender='" + gender + '\'' +
                    ", phone='" + phone + '\'' +
                    ", email='" + email + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }

        public Person create(){
            Person p = new Person(this);
       /*     p.age = age;
            p.name = name;
            p.address = address;
            p.phone = phone;
            p.gender = gender;
            p.email = email;*/

            return p;
        }
    }
}
