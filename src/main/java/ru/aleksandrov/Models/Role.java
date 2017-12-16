package ru.aleksandrov.Models;

import java.util.HashSet;
import java.util.Set;

public class Role {
    private int roleId;
    private String name;
    private Set<Word> words = new HashSet<>(0);

    public Role(){
    }

    public Role(int roleId, String name){
        this.roleId = roleId;
        this.name = name;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int role_id) {
        this.roleId = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", name='" + name + '\'' +
                '}';
    }
}