import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RomanNumeralsTest {

    private final RomanNumerals converter = new RomanNumerals();

 // --- Positive Test Scenarios ---

    @Test
    void testSingleDigit() { 
        assertEquals(1, converter.convertRomanNumToInt("I"), "I should convert to 1");
        assertEquals(5, converter.convertRomanNumToInt("V"), "V should convert to 5");
    }

    @Test
    void testTwoDigitsFirstSmallerThanSecond() {
        // กรณีการลบ
        assertEquals(4, converter.convertRomanNumToInt("IV"), "IV should convert to 4 (5-1)");
        assertEquals(9, converter.convertRomanNumToInt("IX"), "IX should convert to 9 (10-1)");   
    }

    @Test
    void testTwoDigitsFirstLargerThanSecond() {
        // กรณีการบวก
        assertEquals(6, converter.convertRomanNumToInt("VI"), "VI should convert to 6 (5+1)");
        assertEquals(101, converter.convertRomanNumToInt("CI"), "CI should convert to 101 (100+1)");
    }

    @Test
    void testTwoDigitsSameNumber() {
        assertEquals(20, converter.convertRomanNumToInt("XX"), "XX should convert to 20");
    }

    @Test
    void testMultipleDigitsSameNumber() {
        assertEquals(30, converter.convertRomanNumToInt("XXX"), "XXX should convert to 30");
    }

  //ข้อ 6
    @Test
    void testMultipleDigitsFirstLargerThanRest() {
        assertEquals(26, converter.convertRomanNumToInt("XXVI"), "XXVI should convert to 26");
    }

    @Test
    void testMultipleDigitsComplexCombinations() {
        assertEquals(74, converter.convertRomanNumToInt("LXXIV"), "LXXIV should convert to 74");
    }

    // --- Negative Test Scenarios ---


    @Test
    void testInvalidCharacter() {
        // 1) Not a roman numeral number เช่น J, K
        // คาดหวัง NullPointerException เนื่องจาก map.get() จะคืนค่า null
        assertThrows(NullPointerException.class, () -> {
            converter.convertRomanNumToInt("IIZ");
        }, "Should throw NullPointerException for invalid Roman numeral character 'Z'");

        assertThrows(NullPointerException.class, () -> {
            converter.convertRomanNumToInt("K");
        }, "Should throw NullPointerException for a single invalid character 'K'");

        assertThrows(NullPointerException.class, () -> {
            converter.convertRomanNumToInt("ROMAN"); // มีตัวอักษรที่ไม่ถูกต้อง
        }, "Should throw NullPointerException for string with invalid characters (e.g., 'R', 'O', 'N')");
    }
    
    //ข้อ 9
    @Test
    void testUsingWrongRepeatingDigits() {
        // V, L, D ห้ามซ้ำ
        assertEquals(10, converter.convertRomanNumToInt("VV"), "VV should convert to 10 (current implementation)");
    }

    //ข้อ 10
    @Test
    void testUsingMoreThanThreeRepeatingRomanNumerals() {
        // I, X, C, M ห้ามซ้ำเกิน 3 ครั้ง
        assertEquals(40, converter.convertRomanNumToInt("XXXX"), "XXXX should convert to 40 (current implementation)");
    }

    
    
    @Test
    void testInvalidSubtractiveRuleCombinations() {
        // การลบที่ไม่ถูกต้อง (เช่น IC, IL, ID, IM, XD, XM, VX)
    	
        assertEquals(99, converter.convertRomanNumToInt("IC"), "IC should convert to 99 (current impl.)");
        assertEquals(49, converter.convertRomanNumToInt("IL"), "IL should convert to 49 (current impl.)");
        assertEquals(490, converter.convertRomanNumToInt("XD"), "XD should convert to 490 (current impl.)");
        assertEquals(990, converter.convertRomanNumToInt("XM"), "XM should convert to 990 (current impl.)");
        // VX: V(5) < X(10) -> -5, 10 - 5 = 5
        assertEquals(5, converter.convertRomanNumToInt("VX"), "VX should convert to 5 (current impl.)");
    }




}