package frc.robot;


public class Constants {
    //Controller index
    //搖桿編號
    public static final int Drive_Controller_Index =  1;
    public static final int Operator_Controller_Index = 2;

    // Motor index and inversion
    // 馬達編號和正反轉
    public static final int Left_Drive_1_Index = 3;
    public static final boolean Left_Drive_1_Inverted = false;   //motor inverted or not, true: inverted, flase: not inverted
                                                                //馬達是否反轉，true: 反轉，false: 不反轉

    public  static final int Left_Drive_2_Index = 4;
    public static final boolean Left_Drive_2_Inverted = true;

    public static final int Right_Drive_1_Index = 1;
    public static final boolean Right_Drive_1_Inverted = false;

    public static final int Right_Drive_2_Index = 2;
    public static final boolean Right_Drive_2_Inverted = false;

    public static final int Hanger_Index = 5;
    public static final boolean Hanger_Inverted = false;

    public static final int Intake_Index = 6;
    public static final boolean Intake_Inverted = false;

    //Deadband value
    // 死區值
    public static final double Deadband = 0.03;

    // speed constants (1 is max)
    // 速度常數
    public static final double Drive_Speed = 0.8;   
    public static final double Turn_Speed = 0.6;
    public static final double Intake_Speed = 0.5;
    public static final double Hanger_Speed = 0.5;
    



}
