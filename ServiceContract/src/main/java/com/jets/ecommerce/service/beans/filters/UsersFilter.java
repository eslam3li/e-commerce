/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service.beans.filters;


public class UsersFilter {

    private PatternFilter nameFilter;
    private PatternFilter email;
    private PatternFilter phone;

    public UsersFilter() {
    }

    public UsersFilter(PatternFilter nameFilter, PatternFilter email, PatternFilter phone) {
        this.nameFilter = nameFilter;
        this.email = email;
        this.phone = phone;
    }

    public PatternFilter getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(PatternFilter nameFilter) {
        this.nameFilter = nameFilter;
    }

    public PatternFilter getEmail() {
        return email;
    }

    public void setEmail(PatternFilter email) {
        this.email = email;
    }

    public PatternFilter getPhone() {
        return phone;
    }

    public void setPhone(PatternFilter phone) {
        this.phone = phone;
    }

}
