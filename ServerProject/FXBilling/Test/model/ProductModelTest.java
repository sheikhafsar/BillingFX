package model;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class ProductModelTest {

    @Test
    public void getter(){
        UUID id=UUID.randomUUID();
        ProductModel obj=new ProductModel(id,"sahil","8902519003300",100.00,1);
        assertEquals(id,obj.getId());
        assertEquals("sahil",obj.getName());
        assertEquals("8902519003300",obj.getBarcode());
        //assertEquals(100.00,obj.getPrice());
        assertEquals(1,obj.getCount());



    }

    @Test
    public void setter(){

        UUID id=UUID.randomUUID();
        ProductModel obj1=new ProductModel(id,"sahil","8902519003300",100.00,1);
        obj1.setName("rahul");
       assertEquals("rahul",obj1.getName());

        obj1.setBarcode("8902519003301");
        assertEquals("8902519003301",obj1.getBarcode());

        obj1.setId(id);
        assertEquals(id,obj1.getId());

        obj1.setCount(2);
        assertEquals(2,obj1.getCount());

    }

    @Test
    public void getter2(){
        UUID id=UUID.randomUUID();
        ProductModel obj=new ProductModel(id,"sahil","89025190033",2,100.00,100.00,2);

        assertEquals("sahil",obj.getName());
        assertEquals("89025190033",obj.getBarcode());
        assertEquals(id,obj.getId());
        assertEquals(2,obj.getQuantity());
        assertEquals(2,obj.getCount());





    }



}