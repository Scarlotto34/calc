import java.util.ArrayList;
import java.util.Arrays;

class Converter {
    int[] intervals={0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 40, 50, 90, 100};
    String[] numerals={"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XL", "L", "XC", "C"};


     int findFloor(final int number, final int firstIndex, final int lastIndex) {
        if(firstIndex==lastIndex)
            return firstIndex;
        if(intervals[firstIndex]==number)
            return firstIndex;
        if(intervals[lastIndex]==number)
            return lastIndex;
        final int median=(lastIndex+firstIndex)/2;
        if(median==firstIndex)
            return firstIndex;
        if(number == intervals[median])
            return median;
        if(number > intervals[median])
            return findFloor(number, median, lastIndex);
        else
            return findFloor(number, firstIndex, median);

    }

    String toRoman(final int number) {
        int floorIndex=findFloor(number, 0, intervals.length-1);
        if(number==intervals[floorIndex])
            return numerals[floorIndex];
        return numerals[floorIndex]+toRoman(number-intervals[floorIndex]);
    }

    int toArabic(String roman) {
        int result = 0;
        for (int i = intervals.length-1; i >= 0; i-- ) {
            while ((roman.indexOf(numerals[i]) == 0) && !numerals[i].isEmpty()) {
                result += intervals[i];
                roman = roman.substring(numerals[i].length());
            }
        }
        return result;
    }
    public boolean isRoman(String number){
         return Arrays.asList(numerals).contains(number);
    }
}
