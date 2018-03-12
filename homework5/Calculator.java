package hw5;

/**
 * algorithm refer to leetcode, Basic calculator.
 */
public class Calculator {


    /**
     * when no '()'
     * every arithmetic expression start by a number,every number followed by a operator except the last number,
     * there are 2 level operator: '+''-' & '*''/',we need to execute '*''/' operation first,
     * and then execute '+' '-',
     * every time when we read a new operator, we execute last operator
     * for example,
     * we enter : 2 + 5 * 3 - 6 / 2
     * when we read'*', we execute +5, means push '5' into stack
     * when we read '-', we pop 5 and execute'5*3' and push result into stack as a element;
     * @param s
     * @return
     */
    public int calculate_1(String s) {
        int len;
        if(s==null || (len = s.length())==0) return 0;
        LinkedStack<Integer> stack = new LinkedStack<>();  //using a Stack to save a series of Integer
        int num = 0;
        char sign = '+';     //assuming that first number has a prefix "+";
        for(int i=0;i<len;i++){
            //when read a number
            if(Character.isDigit(s.charAt(i))){
                num = num*10+s.charAt(i)-'0';
            }
            //when read a operator
            if((!Character.isDigit(s.charAt(i)) &&' '!=s.charAt(i)) || i==len-1){
                switch(sign){
                    case '-':
                        stack.push(-num);
                        break;
                    case '+':
                        stack.push(num);
                        break;
                    case '*':
                        stack.push(stack.pop()*num);
                        break;
                    case '/':
                        stack.push(stack.pop()/num);
                        break;
                }
                sign = s.charAt(i);
                num = 0;
            }
        }
        int re = 0;
        //pop out elements from stack one by one, due to every minus operation has convert into a prefix"-"
        //we just need to accumulate them;
        while(!stack.isEmpty()){
        re += stack.pop();}
        return re;
    }

}
