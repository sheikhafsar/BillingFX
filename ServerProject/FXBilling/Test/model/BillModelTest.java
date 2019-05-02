package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class BillModelTest {

    @Test
   public void getter(){

        BillModel obj=new BillModel(12,"2019-01-01","raj",21544.00,"karan");
        assertEquals("raj",obj.getCustomerName());
        assertEquals("2019-01-01",obj.getDate());
        assertEquals("raj",obj.getCustomerName());
       // assertEquals(21544.00,obj.getAmount());
        assertEquals("karan",obj.getEmployeeName());

    }

    @Test
    public void setter(){
        BillModel obj1=new BillModel(12,"2019-01-01","raj",21544.00,"karan");
        obj1.setDate("2019-01-02");
        assertEquals("2019-01-02",obj1.getDate());

        obj1.setCustomerName("bahu");
        assertEquals("bahu",obj1.getCustomerName());

        obj1.setEmployeeName("bahubali");
        assertEquals("bahubali",obj1.getEmployeeName());

        obj1.setBill_id(1);
        assertEquals(1,obj1.getBill_id());

        /*
        obj1.setAmount(100.00);
        assertEquals(100.00,obj1.getAmount());
        */
    }







}