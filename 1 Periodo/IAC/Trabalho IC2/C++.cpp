
#include <iostream>
 
int main()
{
    float radius, area;
 
    std::cout << "Raio:  ";
    std::cin >> radius;
    area = 3.14 * radius * radius;
    std::cout << "Raio = "
              << radius << " Area = " << area;
}