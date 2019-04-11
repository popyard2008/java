package com.allInOne;

import java.util.LinkedList;
import java.util.List;

public class letterCombinationsPhone_17 {

    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<>();
        if(digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length()==i){
                System.out.println("before remove ans " + ans.toString());

                String t = ans.remove();
                System.out.println("after remove ans " + ans.toString());

                System.out.println("i = " + i + "; x = " + x + "; t = " + t + "; peek ans = " + ans.peek());
                for(char s : mapping[x].toCharArray()){
                    ans.add(t+s);
                    System.out.println("adding ans " + ans.toString());
                }
                System.out.println("----------");

                System.out.println("ans " + ans.toString());
            }
            System.out.println("");
        }
        return ans;
    }

}

/*
adding ans [a, b]
adding ans [a, b, c]
----------
ans [a, b, c]

before remove ans [a, b, c]
after remove ans [b, c]
i = 1; x = 3; t = a; peek ans = b
adding ans [b, c, ad]
adding ans [b, c, ad, ae]
adding ans [b, c, ad, ae, af]
----------
ans [b, c, ad, ae, af]
before remove ans [b, c, ad, ae, af]
after remove ans [c, ad, ae, af]
i = 1; x = 3; t = b; peek ans = c
adding ans [c, ad, ae, af, bd]
adding ans [c, ad, ae, af, bd, be]
adding ans [c, ad, ae, af, bd, be, bf]
----------
ans [c, ad, ae, af, bd, be, bf]
before remove ans [c, ad, ae, af, bd, be, bf]
after remove ans [ad, ae, af, bd, be, bf]
i = 1; x = 3; t = c; peek ans = ad
adding ans [ad, ae, af, bd, be, bf, cd]
adding ans [ad, ae, af, bd, be, bf, cd, ce]
adding ans [ad, ae, af, bd, be, bf, cd, ce, cf]
----------
 */
