using System;
public class Exercise
{
    public static void Main()
    {
 
        double r,area;
        double PI = 3.14;
        Console.WriteLine("Digite o raio : ");
        r = Convert.ToDouble(Console.ReadLine());
        area = (r*r)*PI;
        Console.WriteLine("Area do Circulo: {0}", area);
        Console.Read();
    }
}