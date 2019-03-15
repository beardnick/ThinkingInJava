package Demo.ChapterFour;

import static Util.Print.*;

/**
 * Created by asus on 2017/12/1.
 */
public class BreakContinue {

    public static void main(String[] args){
        int i = 0;
        outer:
        for(;true;){
            inner:
            for(;i < 10 ; i ++){
                print(" i = " + i);
                if(i == 2){
                    print("continue");
                    continue ;
                }
                if( i == 3){
                    print("break");
                    i ++ ;
                    break ;
                }

                if(i == 7){
                    print("continue outer");
                    i ++;
                    continue outer;
                }

                if(i == 8){
                    print("break outer");
                    break outer;
                }

                for( int k = 0 ; k < 5 ; k ++){
                    if( k == 3){
                        print("continue inner");
                        continue inner;
                    }
                }

            }
        }
    }
}
