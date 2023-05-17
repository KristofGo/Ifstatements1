/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mcv.Monopoly;

/**
 *
 * @author student
 */
public class Card {
    int houseCost; // cost to buy house
    int propertyCost; // cost to buy property
    int OGvalue; // payment for landing 
    int house1; // payment for 1 house 
    int house2; // payment for 2 house 
    int house3; // payment for 3 house 
    int house4; // payment for 4 house 
    int hotel; // payment for hotel 
    int owner; // owner 
    int houses; // how many houses 
    boolean color; // do they have complete set
    int colorPrice; // payment for color
    public Card (int PC, int HC, int OG, int CP, int H1, int H2, int H3, int H4, int H5) {
        OGvalue = OG; // dont change
        house1 = H1; // dont change
        house2 = H2; // dont change
        house3 = H3; // dont change
        house4 = H4; // dont change
        hotel = H5;  // dont change
        houses = 0; //changable
        owner = 0; // changable
        color = false; // changable
        houseCost = HC; // dont change
        propertyCost = PC; // dont change
        colorPrice = CP; // dont change
    }
    public void setOwner ( int O) { // setters 3
        owner = O;
    }
    public void setHouses ( int H) {
        houses = H;
    }
    public void setColor(boolean C) {
        color = C;
    }
    public int getOwner () { // getters 12?
        return owner;
    }
    
    public int getHouses () {
        return houses;
    }
    
    public int getOGvalue() {
        return OGvalue;
    }
    public int getHouse1() {
        return house1;
    }
    public int getHouse2() {
        return house2;
    }
    public int getHouse3() {
        return house3;
    }
    public int getHouse4() {
        return house4;
    }
    public int getHotel() {
        return hotel;
    }
    public int getHouseCost() {
        return houseCost;
    }
    public int getPropertyCost() {
        return propertyCost;
    }
    public boolean getColor() {
        return color;
    }
    
    public int getColorPrice() {
        return colorPrice;
    }
}
