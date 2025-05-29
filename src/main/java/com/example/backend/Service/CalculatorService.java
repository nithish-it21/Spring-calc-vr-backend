package com.example.backend.Service;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class CalculatorService {
    public String calculate(String expression) {
        if(isInvalid(expression)){
            return "Invalid Expression";
        }
        Pattern pattern = Pattern.compile("\\d+|[+\\-*/]");
        Matcher matcher = pattern.matcher(expression);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        List<String> First_iter =  new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String character = list.get(i);
            if(character.equals("*") || character.equals("/")){
                int left = Integer.parseInt(First_iter.remove(First_iter.size() - 1));
                int right = Integer.parseInt(list.get(++i));
                int result = character.equals("*") ? (left * right) : (left / right);
                First_iter.add(String.valueOf(result));
            }
            else{
                First_iter.add(character);
            }
        }
        int result = Integer.parseInt(First_iter.get(0));
        for(int i = 1; i < First_iter.size(); i+=2){
            String operator = First_iter.get(i);
            int next = Integer.parseInt(First_iter.get(i+1));
            if(operator.equals("+")){
                result = result + next;
            }else{
                result = result - next;
            }
        }
        return String.valueOf(result);
    }

    public boolean isInvalid(String expression) {
        if (expression == null || expression.isEmpty()) {
            return true;
        }
        char first = expression.charAt(0);
        char last = expression.charAt(expression.length() - 1);
        if (!Character.isDigit(first) || !Character.isDigit(last)) {
            return true;
        }
        boolean hasOperator = false;
        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);
            if (!Character.isDigit(current) && "+-*/".indexOf(current) == -1) {
                return true;
            }
            if (i > 0) {
                char prev = expression.charAt(i - 1);
                if (!Character.isDigit(prev) && !Character.isDigit(current)) {
                    return true;
                }
            }
            if ("+-*/".indexOf(current) != -1) {
                hasOperator = true;
            }
        }
        if (!hasOperator) {
            return true;
        }
        return false;
    }
}