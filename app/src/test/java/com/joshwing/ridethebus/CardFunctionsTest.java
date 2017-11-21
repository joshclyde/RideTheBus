package com.joshwing.ridethebus;

import org.junit.Test;

import classes.CardFunctions;

import static junit.framework.Assert.assertEquals;

/**
 * Created by joshclyde on 11/20/17.
 */

public class CardFunctionsTest {

    @Test
    public void minDiamond() {
        int card = 0;
        assertEquals(0, CardFunctions.getCardNumber(card));
        assertEquals(0, CardFunctions.getCardSuit(card));
        assertEquals(true, CardFunctions.isRed(card));
        assertEquals(false, CardFunctions.isBlack(card));
        assertEquals(true, CardFunctions.isDiamond(card));
        assertEquals(false, CardFunctions.isHeart(card));
        assertEquals(false, CardFunctions.isClub(card));
        assertEquals(false, CardFunctions.isSpade(card));
    }

    @Test
    public void maxDiamond() {
        int card = 12;
        assertEquals(12, CardFunctions.getCardNumber(card));
        assertEquals(0, CardFunctions.getCardSuit(card));
        assertEquals(true, CardFunctions.isRed(card));
        assertEquals(false, CardFunctions.isBlack(card));
        assertEquals(true, CardFunctions.isDiamond(card));
        assertEquals(false, CardFunctions.isHeart(card));
        assertEquals(false, CardFunctions.isClub(card));
        assertEquals(false, CardFunctions.isSpade(card));
    }

    @Test
    public void minHeart() {
        int card = 13;
        assertEquals(0, CardFunctions.getCardNumber(card));
        assertEquals(1, CardFunctions.getCardSuit(card));
        assertEquals(true, CardFunctions.isRed(card));
        assertEquals(false, CardFunctions.isBlack(card));
        assertEquals(false, CardFunctions.isDiamond(card));
        assertEquals(true, CardFunctions.isHeart(card));
        assertEquals(false, CardFunctions.isClub(card));
        assertEquals(false, CardFunctions.isSpade(card));
    }

    @Test
    public void maxHeart() {
        int card = 25;
        assertEquals(12, CardFunctions.getCardNumber(card));
        assertEquals(1, CardFunctions.getCardSuit(card));
        assertEquals(true, CardFunctions.isRed(card));
        assertEquals(false, CardFunctions.isBlack(card));
        assertEquals(false, CardFunctions.isDiamond(card));
        assertEquals(true, CardFunctions.isHeart(card));
        assertEquals(false, CardFunctions.isClub(card));
        assertEquals(false, CardFunctions.isSpade(card));
    }

    @Test
    public void minClub() {
        int card = 26;
        assertEquals(0, CardFunctions.getCardNumber(card));
        assertEquals(2, CardFunctions.getCardSuit(card));
        assertEquals(false, CardFunctions.isRed(card));
        assertEquals(true, CardFunctions.isBlack(card));
        assertEquals(false, CardFunctions.isDiamond(card));
        assertEquals(false, CardFunctions.isHeart(card));
        assertEquals(true, CardFunctions.isClub(card));
        assertEquals(false, CardFunctions.isSpade(card));
    }

    @Test
    public void maxClub() {
        int card = 38;
        assertEquals(12, CardFunctions.getCardNumber(card));
        assertEquals(2, CardFunctions.getCardSuit(card));
        assertEquals(false, CardFunctions.isRed(card));
        assertEquals(true, CardFunctions.isBlack(card));
        assertEquals(false, CardFunctions.isDiamond(card));
        assertEquals(false, CardFunctions.isHeart(card));
        assertEquals(true, CardFunctions.isClub(card));
        assertEquals(false, CardFunctions.isSpade(card));
    }


    @Test
    public void minSpade() {
        int card = 39;
        assertEquals(0, CardFunctions.getCardNumber(card));
        assertEquals(3, CardFunctions.getCardSuit(card));
        assertEquals(false, CardFunctions.isRed(card));
        assertEquals(true, CardFunctions.isBlack(card));
        assertEquals(false, CardFunctions.isDiamond(card));
        assertEquals(false, CardFunctions.isHeart(card));
        assertEquals(false, CardFunctions.isClub(card));
        assertEquals(true, CardFunctions.isSpade(card));
    }

    @Test
    public void maxSpade() {
        int card = 51;
        assertEquals(12, CardFunctions.getCardNumber(card));
        assertEquals(3, CardFunctions.getCardSuit(card));
        assertEquals(false, CardFunctions.isRed(card));
        assertEquals(true, CardFunctions.isBlack(card));
        assertEquals(false, CardFunctions.isDiamond(card));
        assertEquals(false, CardFunctions.isHeart(card));
        assertEquals(false, CardFunctions.isClub(card));
        assertEquals(true, CardFunctions.isSpade(card));
    }


}
