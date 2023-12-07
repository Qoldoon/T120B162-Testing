package edu.ktu.ds.lab3.demo;

import junit.framework.TestCase;
import org.junit.Assert;

import java.util.InputMismatchException;

public class CarTest extends TestCase {

    public void testParse() {
        Car car = new Car("Renault Laguna 2001 115900 7500");
        Assert.assertEquals("Renault", car.getMake());
    }

    public void testTestToString() {
        Car car = new Car("Renault Laguna 2001 115900 7500");

        Assert.assertEquals("Renault_Laguna:2001 115900 7500.0", car.toString());
    }


    public void testGetModel() {
        Car car = new Car("Renault Laguna 2001 115900 7500");
        Assert.assertEquals("Laguna", car.getModel());

    }

    public void testGetYear() {
        Car car = new Car("Renault Laguna 2001 115900 7500");
        Assert.assertEquals(2001, car.getYear());
    }

    public void testGetMileage() {
        Car car = new Car("Renault Laguna 2001 115900 7500");
        Assert.assertEquals(115900, car.getMileage());
    }

    public void testGetPrice() {
        Car car = new Car("Renault Laguna 2001 115900 7500");
        Assert.assertEquals(7500, car.getPrice(), 0);
    }

    public void testSetPrice() {
        Car car = new Car("Renault Laguna 2001 115900 7500");
        car.setPrice(10);
        Assert.assertEquals(10, car.getPrice(), 0);
    }

    public void testSetMileage() {
        Car car = new Car("Renault Laguna 2001 115900 7500");
        car.setMileage(1);
        Assert.assertEquals(1, car.getMileage());
    }

    public void testTestEquals() {
        Car car1 = new Car("Renault Laguna 2001 115900 7500");
        Car car2 = new Car();
        Car car3 = new Car("Renault Laguna 2001 115900 7500");
        Assert.assertNotEquals(car1, car2);
        Assert.assertEquals(car1, car3);
        Assert.assertNotEquals("car2", car1);

    }

    public void testTestHashCode() {
        Car car1 = new Car("Renault Laguna 2001 115900 7500");
        Car car2 = new Car(new Car.Builder());
        Car car3 = new Car("Renault", "Laguna",2001,115900,7500);
        Assert.assertNotEquals(car1.hashCode(), car2.hashCode());
        Assert.assertEquals(car1.hashCode(), car3.hashCode());
    }

    public void testParseExceptions(){
        try{
            Car car1 = new Car("Renault 2001 Laguna 115900 7500");
            Car car2 = new Car("Renault Laguna");
        }
        catch(Exception e)
        {
            Assert.fail("Exception was thrown");
        }
    }

    public void testCarBuilder(){
        Car car1 = new Car.Builder().buildRandom();
        Assert.assertNotEquals(-1, car1.getYear());
        Car car2 = new Car.Builder().make("Renault").model("Laguna").year(2001).mileage(115900).price(7500).build();
        Assert.assertEquals("Renault", car2.getMake());
    }
}