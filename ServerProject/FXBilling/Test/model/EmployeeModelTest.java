package model;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class EmployeeModelTest {

    @Test
    public void getInstance(){

        UUID id=UUID.randomUUID();
        //String idu=id.toString();
        EmployeeModel m1=new EmployeeModel(id,"aa","aa","raj","nadaf","rajahmed@gmail.com","8520852085","margao");
        assertEquals("aa",m1.getUsername());
        assertEquals("aa",m1.getPassword());
        assertEquals("raj",m1.getFirstname());
        assertEquals(id,m1.getId());
        assertEquals("rajahmed@gmail.com",m1.email);
        assertEquals("8520852085",m1.getPhone());
        assertEquals("margao",m1.getAddress());
    }

    @Test
    public void setIntance(){
        UUID id=UUID.randomUUID();
        EmployeeModel m2=new EmployeeModel(id,"aa","aa","raj","nadaf","rajahmed@gmail.com","8520852085","margao");
        m2.setFirstname("ali");
        assertEquals("ali",m2.getFirstname());

        m2.setEmail("alinadaf@gmail");
        assertEquals("alinadaf@gmail",m2.getEmail());

        m2.setPhone("100");
        assertEquals("100",m2.getPhone());

        m2.setAddress("panjim");
        assertEquals("panjim",m2.getAddress());


    }

}