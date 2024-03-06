package com.company.project.employes;

/**
 * @author snd00 created at 11.02.2024
 * @project docker-db-ui-boot
 */
public interface Employee extends Comparable <Employee>{
    int getId();

    Profile getProfile();
}