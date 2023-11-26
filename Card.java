/* CSCI213 Assignment 2
 * --------------------
 * File name: Card.java
 * Author: Minton Yap Li Hui
 * Student Number: 5987398
 * Description: Contains details of card info, including getters and setters method
 */

public class Card{

private String name;
private String description;
private int earth;
private int water;
private int air;
private int fire;
private int total;

public Card(String n, String d, int e, int w, int a, int f) {
name = n;
description = d;
earth = e;
water = w;
air = a;
fire = f;
}

public String getName() {
return name;
}

public String getDescription() {
return description;
}

public int getEarth() {
return earth;
}

public int getWater() {
return water;
}

public int getAir() {
return air;
}

public int getFire() {
return fire;
}

public void setEarth(int e) {
this.earth=e;
}

public void setWater(int w) {
this.water=w;
}

public void setAir(int a) {
this.air=a;
}

public void setFire(int f) {
this.fire=f;
}

public String toString() {
	total = earth + water + air + fire;
	return name + " - " + description + " E:" + earth + " W:" + water + " A:" + air + " F:" + fire + " T:" + total;
}

}